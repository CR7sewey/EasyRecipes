package com.example.myapplication.randomList.data

import android.accounts.NetworkErrorException
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.randomList.data.local.RecipesListLocalDataSource
import com.example.myapplication.randomList.data.remote.RecipesListRemoteDataSource

class RecipesListRepository(private val recipesListLocalDataSource: RecipesListLocalDataSource, private val recipesListRemoteDataSource: RecipesListRemoteDataSource) {

    suspend fun getAllRecipes(): Result<List<Recipe>> {
        return try {
            var getLocalRecipes = recipesListLocalDataSource.getAllRecipes()
            val getRemoteRecipes = recipesListRemoteDataSource.getAllMovies()
            if (getRemoteRecipes.isSuccess) {
                val recipes = getRemoteRecipes.getOrNull() ?: emptyList<Recipe>()
                when {
                    recipes.isNotEmpty() -> {
                        recipesListLocalDataSource.deleteAllRecipes()
                        recipesListLocalDataSource.insertAllRecipes(recipes)
                    }
                }
                getLocalRecipes = recipesListLocalDataSource.getAllRecipes()
                Result.success(getLocalRecipes)
            }
            else {
                if (!getLocalRecipes.isEmpty()) {
                    Result.success(getLocalRecipes)
                }
                else {
                    Result.failure(NetworkErrorException(getRemoteRecipes.exceptionOrNull()?.message.toString()))
                }

            }
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

}