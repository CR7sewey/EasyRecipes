package com.example.myapplication.searchedRecipes.data.remote

import com.example.myapplication.common.data.model.SearchedRecipe

interface RemoteDataSource {
    suspend fun searchRecipes(query: String): Result<List<SearchedRecipe>?>
}