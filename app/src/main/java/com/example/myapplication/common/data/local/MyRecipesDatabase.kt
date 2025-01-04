package com.example.myapplication.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipesEntity::class], version = 1)
abstract class MyRecipesDatabase: RoomDatabase() {
    abstract fun getRecipesDao(): RecipesDao
}