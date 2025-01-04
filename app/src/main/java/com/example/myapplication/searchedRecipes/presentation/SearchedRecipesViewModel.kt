package com.example.myapplication.searchedRecipes.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.myapplication.MyRecipesApplication
import com.example.myapplication.common.data.model.SearchedRecipe
import com.example.myapplication.common.data.remote.model.SearchRecipeDto
import com.example.myapplication.randomList.presentation.ui.RecipeUiData
import com.example.myapplication.randomList.presentation.ui.RecipesListUiState
import com.example.myapplication.searchedRecipes.data.SearchedRecipesListRepository
import com.example.myapplication.searchedRecipes.data.remote.SearchedRecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchedRecipesViewModel(private val searchedRecipesListRepository: SearchedRecipesListRepository): ViewModel() {

    private val _uiRecipes = MutableStateFlow<List<SearchedRecipe>>(emptyList<SearchedRecipe>())
    val uiRecipes: StateFlow<List<SearchedRecipe>> = _uiRecipes

    private val _uiErrorFetching = MutableStateFlow<String>("")
    val uiErrorFetching: StateFlow<String> = _uiErrorFetching

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyRecipesApplication
                val repository = application.searchRepository
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return SearchedRecipesViewModel(repository) as T
            }

        }
    }

    fun fetchData(query: String) {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            _uiErrorFetching.value = ""
            val response = searchedRecipesListRepository.searchRecipes(query)
            if (response.isSuccess) {
                val recipes = response.getOrNull()
                if (recipes != null) {
                    _uiRecipes.value = recipes
                }
                }

            else {
                Log.d("MainActivity", "Request Error :: ${response.exceptionOrNull()?.message.toString()}")
                val ex = response.exceptionOrNull()
                _uiErrorFetching.value = "Some error..."
                if (ex is NetworkErrorException) {
                    _uiErrorFetching.value = "No internet connection..."
                }
        }
        }

    }

}