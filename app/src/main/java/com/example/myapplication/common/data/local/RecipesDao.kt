package com.example.myapplication.common.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.common.data.model.Recipe

@Dao
interface RecipesDao {

    @Query("select * from recipesentity")
    suspend fun getAllRecipes(): List<RecipesEntity>

    @Query("select * from recipesentity where title LIKE :query")
    suspend fun searchByTitle(query: String): List<RecipesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<RecipesEntity>)

    @Query("delete from recipesentity")
    suspend fun deleteAllRecipes()

}