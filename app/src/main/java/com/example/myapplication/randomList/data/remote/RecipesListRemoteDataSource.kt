package com.example.myapplication.randomList.data.remote

import android.accounts.NetworkErrorException
import com.example.myapplication.common.data.local.RecipesEntity
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.common.data.remote.model.RecipeDTO
import okhttp3.Response

class RecipesListRemoteDataSource(private val randomListService: RandomListService) {

    suspend fun getAllMovies(): Result<List<Recipe>?> {
        return try {
            val recipesFromAPI = randomListService.getRandomRecipes()
            var recipesConverted = emptyList<Recipe>()
            if (recipesFromAPI.isSuccessful) {
                val recipes = recipesFromAPI.body()?.recipes ?: emptyList<RecipeDTO>()
                if (!recipes.isEmpty()) {
                    recipesConverted = recipes.map { recipe -> Recipe(id = recipe.id, title = recipe.title, image = recipe.image, servings = recipe.servings, readyInMinutes = recipe.readyInMinutes, summary = recipe.summary) }
                }
                Result.success(recipesConverted)
            }
            else {
                Result.failure(NetworkErrorException(recipesFromAPI.message()))

            }
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }

}