package com.example.myapplication

import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.common.data.remote.model.RecipeResponse
import com.example.myapplication.common.data.remote.model.SearchRecipesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/random?number=20")
    fun getRandomRecipes(): Call<RecipeResponse>

    @GET("recipes/{id}/information?includeNutrition=false")
    fun getRecipeInfo(@Path("id") id: String): Call<RecipeDTO>

    @GET("recipes/complexSearch")
    fun searchRecipes(@Query("query") query: String): Call<SearchRecipesResponse>
}

// curl --location 'https://api.spoonacular.com/recipes/random?limitLicense=true&tags=ipsum%20ea%20proident%20amet%20occaecat&number=10&apiKey=%3CAPI%20Key%3E'