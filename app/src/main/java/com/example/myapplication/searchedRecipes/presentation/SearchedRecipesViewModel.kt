package com.example.myapplication.searchedRecipes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.common.data.remote.RetroFitClient
import com.example.myapplication.common.data.remote.model.SearchRecipeDto
import com.example.myapplication.searchedRecipes.data.SearchedRecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchedRecipesViewModel(private val searchedRecipeService: SearchedRecipeService): ViewModel() {

    private val _uiRecipes = MutableStateFlow<List<SearchRecipeDto>>(emptyList<SearchRecipeDto>())
    val uiRecipes: StateFlow<List<SearchRecipeDto>> = _uiRecipes

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = RetroFitClient.retrofit.create(SearchedRecipeService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return SearchedRecipesViewModel(application as SearchedRecipeService) as T
            }

        }
    }

    fun fetchData(query: String) {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            val response = searchedRecipeService.searchRecipes(query)
            if (response.isSuccessful) {
                _uiRecipes.value = response.body()?.results ?: emptyList<SearchRecipeDto>()
            }
            else {
                Log.d("SearchedRecipesViewModel", "Request Error :: ${response.errorBody()}")
            }
        }

    }

}