package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.common.data.local.MyRecipesDatabase
import com.example.myapplication.common.data.remote.RetroFitClient
import com.example.myapplication.randomList.data.RecipesListRepository
import com.example.myapplication.randomList.data.local.RecipesListLocalDataSource
import com.example.myapplication.randomList.data.remote.RandomListService
import com.example.myapplication.randomList.data.remote.RecipesListRemoteDataSource

class MyRecipesApplication: Application() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyRecipesDatabase::class.java, "recipes-database"
        ).build()
    }

    private val remoteDataSource by lazy {
        RecipesListRemoteDataSource(RetroFitClient.retrofit.create(RandomListService::class.java))
    }

    private val localDataSource by lazy {
        RecipesListLocalDataSource(recipesDao = db.getRecipesDao())
    }

    val repository: RecipesListRepository by lazy {
        RecipesListRepository(localDataSource, remoteDataSource)
    }


}