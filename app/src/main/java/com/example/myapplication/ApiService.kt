package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("recipes/random?number=20")
    fun getRandomRecipes(): Call<RecipeResponse>

    @GET("recipes/{id}/information?includeNutrition=false")
    fun getRecipeInfo(@Path("id") id: String): Call<RecipeDTO>
}

// curl --location 'https://api.spoonacular.com/recipes/random?limitLicense=true&tags=ipsum%20ea%20proident%20amet%20occaecat&number=10&apiKey=%3CAPI%20Key%3E'