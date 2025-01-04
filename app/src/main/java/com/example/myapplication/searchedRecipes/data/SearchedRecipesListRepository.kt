package com.example.myapplication.searchedRecipes.data

import android.accounts.NetworkErrorException
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.common.data.model.SearchedRecipe
import com.example.myapplication.searchedRecipes.data.local.SearchedRecipesLocalDataSource
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipesRemoteDataSource

class SearchedRecipesListRepository(private val searchedRecipesLocalDataSource: SearchedRecipesLocalDataSource, private val searchedRecipesRemoteDataSource: SearchedRecipesRemoteDataSource) {

    suspend fun searchRecipes(query: String): Result<List<SearchedRecipe>> {
        return try {
            var getLocalRecipes = searchedRecipesLocalDataSource.searchRecipes(query)
            println(getLocalRecipes.toString())
            val getRemoteRecipes = searchedRecipesRemoteDataSource.searchRecipes(query)
            if (getRemoteRecipes.isSuccess) {
                Result.success(getRemoteRecipes)
            } else {
                if (!getLocalRecipes.isEmpty()) {
                    println("AQUUI NO SITIO")
                    Result.success(getLocalRecipes)
                } else {
                    println("AQUUI NO SITIO 2")
                    Result.failure(NetworkErrorException(getRemoteRecipes.exceptionOrNull()?.message))
                }

            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<List<SearchedRecipe>>
    }


}