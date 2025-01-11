package com.example.myapplication.randomList.data.dependencyInjection

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import com.example.myapplication.randomList.data.local.LocalDataSource
import com.example.myapplication.randomList.data.local.RecipesListLocalDataSource
import com.example.myapplication.randomList.data.remote.RandomListService
import com.example.myapplication.randomList.data.remote.RecipesListRemoteDataSource
import com.example.myapplication.randomList.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RandomListModule {

    @Provides
    fun providesRandomListService(retrofit: Retrofit): RandomListService {
        return retrofit.create(RandomListService::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
interface RandomListModuleInterface {

    @Binds
    fun bindLocalDataSource(impl: RecipesListLocalDataSource): LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: RecipesListRemoteDataSource): RemoteDataSource
}