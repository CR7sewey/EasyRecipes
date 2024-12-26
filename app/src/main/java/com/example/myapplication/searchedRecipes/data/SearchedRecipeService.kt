package com.example.myapplication.searchedRecipes.data

import com.example.myapplication.common.model.SearchRecipesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchedRecipeService {
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(@Query("query") query: String): Response<SearchRecipesResponse>
}