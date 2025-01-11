package com.example.myapplication.searchedRecipes.data.local

import com.example.myapplication.common.data.local.RecipesDao
import com.example.myapplication.common.data.model.SearchedRecipe

class SearchedRecipesLocalDataSource(private val recipesDao: RecipesDao): LocalDataSource {

    override suspend fun searchRecipes(query: String): List<SearchedRecipe> {
        val recipes = recipesDao.searchByTitle(query)
        return recipes.map { recipe -> SearchedRecipe(id = recipe.id, title = recipe.title, image = recipe.image) }
    }
}