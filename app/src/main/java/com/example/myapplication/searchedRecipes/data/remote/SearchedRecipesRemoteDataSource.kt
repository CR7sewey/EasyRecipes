package com.example.myapplication.searchedRecipes.data.remote

import android.accounts.NetworkErrorException
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.common.data.model.SearchedRecipe
import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.common.data.remote.model.SearchRecipeDto
import kotlin.printStackTrace

class SearchedRecipesRemoteDataSource(private val searchedRecipeService: SearchedRecipeService) {

    suspend fun searchRecipes(query: String): Result<List<SearchedRecipe>?> {
        return try {
            val recipesFromAPI = searchedRecipeService.searchRecipes(query)
            var recipesConverted = emptyList<SearchedRecipe>()
            if (recipesFromAPI.isSuccessful) {
                val recipes = recipesFromAPI.body()?.results ?: emptyList<SearchRecipeDto>()
                if (!recipes.isEmpty()) {
                    recipesConverted = recipes.map { recipe -> SearchedRecipe(id = recipe.id, title = recipe.title, image = recipe.image) }
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
