package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("recipes/random?number=20")
    fun getRandomRecipes(): Call<RecipeResponse>
}

// curl --location 'https://api.spoonacular.com/recipes/random?limitLicense=true&tags=ipsum%20ea%20proident%20amet%20occaecat&number=10&apiKey=%3CAPI%20Key%3E'