package com.example.myapplication.randomList.data.local

import com.example.myapplication.common.data.model.Recipe

interface LocalDataSource {
    suspend fun getAllRecipes(): List<Recipe>

    suspend fun insertAllRecipes(recipes: List<Recipe>)

    suspend fun deleteAllRecipes()

}