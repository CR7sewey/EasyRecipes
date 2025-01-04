package com.example.myapplication.common.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipesDao {

    @Query("select * from recipesentity")
    fun getRecipes(): List<RecipesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipes()

    @Delete
    fun deleteAllRecipes()

}