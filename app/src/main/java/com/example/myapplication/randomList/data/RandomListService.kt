package com.example.myapplication.randomList.data

import com.example.myapplication.common.data.remote.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET

interface RandomListService {
    @GET("recipes/random?number=20")
    suspend fun getRandomRecipes(): Response<RecipeResponse>
}