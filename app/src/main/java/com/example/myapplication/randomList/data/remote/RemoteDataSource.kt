package com.example.myapplication.randomList.data.remote

import com.example.myapplication.common.data.model.Recipe

interface RemoteDataSource {

    suspend fun getAllMovies(): Result<List<Recipe>?>
}