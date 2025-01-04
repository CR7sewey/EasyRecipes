package com.example.myapplication.recipeDetails.data

import com.example.myapplication.common.data.remote.model.RecipeDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeDetailsService {
    @GET("recipes/{id}/information?includeNutrition=false")
    suspend fun getRecipeInfo(@Path("id") id: String): Response<RecipeDTO>
}