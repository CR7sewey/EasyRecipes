package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.common.data.local.MyRecipesDatabase

class MyRecipesApplication: Application() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyRecipesDatabase::class.java, "recipes-database"
        ).build()
    }
}