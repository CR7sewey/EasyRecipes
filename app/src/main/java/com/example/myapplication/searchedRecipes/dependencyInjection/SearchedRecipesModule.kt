package com.example.myapplication.searchedRecipes.dependencyInjection

import com.example.myapplication.searchedRecipes.data.local.LocalDataSource
import com.example.myapplication.searchedRecipes.data.local.SearchedRecipesLocalDataSource
import com.example.myapplication.searchedRecipes.data.remote.RemoteDataSource
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipeService
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SearchedRecipesModule {

    @Provides
    fun providesSearchedRecipeService(retrofit: Retrofit): SearchedRecipeService {
        return retrofit.create(SearchedRecipeService::class.java)
    }
}


@Module // criar container
@InstallIn(ViewModelComponent::class)
interface MovieListModuleBinding {

    @Binds
    fun bindLocalDataSource(impl: SearchedRecipesLocalDataSource): LocalDataSource

    @Binds
    fun bindRemoteDataSource(impl: SearchedRecipesRemoteDataSource): RemoteDataSource

}
