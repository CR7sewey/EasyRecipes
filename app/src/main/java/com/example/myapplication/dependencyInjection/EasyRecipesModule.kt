package com.example.myapplication.dependencyInjection

import android.app.Application
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.example.myapplication.MyRecipesApplication
import com.example.myapplication.common.data.local.MyRecipesDatabase
import com.example.myapplication.common.data.local.RecipesDao
import com.example.myapplication.common.data.remote.RetroFitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class EasyRecipesModule {

    @Provides
    fun provideRoomDataBase(application: Application): MyRecipesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            MyRecipesDatabase::class.java, "recipes-database"
        ).build()
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return RetroFitClient.retrofit
    }

    @Provides
    fun providesRecipesDao(database: MyRecipesDatabase): RecipesDao {
        return database.getRecipesDao()
    }

    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }



}