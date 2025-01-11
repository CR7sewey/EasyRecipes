package com.example.myapplication.recipeDetails.dependencyInjection

import com.example.myapplication.recipeDetails.data.RecipeDetailsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class RecipeDetailsModule {

    @Provides
    fun providesRandomListService(retrofit: Retrofit): RecipeDetailsService {
        return retrofit.create(RecipeDetailsService::class.java)
    }
}

