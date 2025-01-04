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
    fun getAllRecipes(): List<RecipesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipes(recipes: List<RecipesEntity>)

    @Delete
    fun deleteAllRecipes()

}