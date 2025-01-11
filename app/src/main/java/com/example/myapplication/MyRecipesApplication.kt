package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.common.data.local.MyRecipesDatabase
import com.example.myapplication.common.data.remote.RetroFitClient
import com.example.myapplication.randomList.data.RecipesListRepository
import com.example.myapplication.randomList.data.local.RecipesListLocalDataSource
import com.example.myapplication.randomList.data.remote.RandomListService
import com.example.myapplication.randomList.data.remote.RecipesListRemoteDataSource
import com.example.myapplication.searchedRecipes.data.SearchedRecipesListRepository
import com.example.myapplication.searchedRecipes.data.local.SearchedRecipesLocalDataSource
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipeService
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipesRemoteDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyRecipesApplication: Application() {

    /* private val db by lazy {
         Room.databaseBuilder(
             applicationContext,
             MyRecipesDatabase::class.java, "recipes-database"
         ).build()
     }

     private val randomListService by lazy {
         RetroFitClient.retrofit.create(RandomListService::class.java)
     }

     private val remoteDataSource by lazy {
         RecipesListRemoteDataSource(randomListService)
     }

     private val localDataSource by lazy {
         RecipesListLocalDataSource(recipesDao = db.getRecipesDao())
     }

     val repository: RecipesListRepository by lazy {
         RecipesListRepository(localDataSource, remoteDataSource)
     }*/

    /*
    private val searchedRecipeService by lazy {
        RetroFitClient.retrofit.create(SearchedRecipeService::class.java)
    }

    private val searchedRemoteDataSource by lazy {
        SearchedRecipesRemoteDataSource(searchedRecipeService)
    }

    private val searchedLocalDataSource by lazy {
        SearchedRecipesLocalDataSource(recipesDao = db.getRecipesDao())
    }

    val searchRepository: SearchedRecipesListRepository by lazy {
        SearchedRecipesListRepository(searchedLocalDataSource, searchedRemoteDataSource)
    }

    */
}