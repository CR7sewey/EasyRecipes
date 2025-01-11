package com.example.myapplication.searchedRecipes.data.local

import com.example.myapplication.common.data.model.SearchedRecipe

interface LocalDataSource {
    suspend fun searchRecipes(query: String): List<SearchedRecipe>
}