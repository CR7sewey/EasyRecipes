package com.example.myapplication.randomList.data.local

import androidx.room.ColumnInfo
import com.example.myapplication.common.data.local.RecipesDao
import com.example.myapplication.common.data.local.RecipesEntity
import com.example.myapplication.common.data.model.Recipe

class RecipesListLocalDataSource(private val recipesDao: RecipesDao): LocalDataSource {

    override suspend fun getAllRecipes(): List<Recipe> {
        val recipes = recipesDao.getAllRecipes()
        return recipes.map { recipe -> Recipe(id = recipe.id, title = recipe.title, image = recipe.image, servings = recipe.servings, readyInMinutes = recipe.readyInMinutes, summary = recipe.summary) }
    }

    override suspend fun insertAllRecipes(recipes: List<Recipe>) {
        val recipesEntity = recipes.map { recipe -> RecipesEntity(id = recipe.id, title = recipe.title, image = recipe.image, servings = recipe.servings, readyInMinutes = recipe.readyInMinutes, summary = recipe.summary) }
        recipesDao.insertAllRecipes(recipesEntity)
    }

    override suspend fun deleteAllRecipes() {
        recipesDao.deleteAllRecipes()
    }
}