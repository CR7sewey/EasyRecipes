package com.example.myapplication.randomList.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.myapplication.MyRecipesApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.myapplication.common.data.model.Recipe
import com.example.myapplication.common.data.remote.model.RecipeDTO
import com.example.myapplication.dependencyInjection.DispatcherIO
import com.example.myapplication.randomList.data.RecipesListRepository
import com.example.myapplication.randomList.data.remote.RandomListService
import com.example.myapplication.randomList.presentation.ui.RecipeUiData
import com.example.myapplication.randomList.presentation.ui.RecipesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class RandomRecipesViewModel @Inject constructor(private val recipesListRepository: RecipesListRepository, @DispatcherIO private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _uiRandomRecipes = MutableStateFlow<RecipesListUiState>(RecipesListUiState())
    val uiRandomRecipes: StateFlow<RecipesListUiState> = _uiRandomRecipes

    /*private val _uiErrorFetching = MutableStateFlow<String>("")
    val uiErrorFetching: StateFlow<String> = _uiErrorFetching*/

    init {
        fetchData()
    }

    /*companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                //val application = RetroFitClient.retrofit.create(RandomListService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyRecipesApplication
                val repository = application.repository
                //val savedStateHandle = extras.createSavedStateHandle()
                return RandomRecipesViewModel(repository) as T
            }

        }
    }*/

    private fun fetchData() {

        viewModelScope.launch(dispatcher) { // Suspend configuration != callback one
            val response = recipesListRepository.getAllRecipes()
            _uiRandomRecipes.value = RecipesListUiState(isLoading = true)
            if (response.isSuccess) {
                val recipes = response.getOrNull()
                if (recipes != null) {
                    val recipesConverted = recipes.map { recipe -> RecipeUiData(id = recipe.id, title = recipe.title, image = recipe.image, servings = recipe.servings, readyInMinutes = recipe.readyInMinutes, summary = recipe.summary) }
                    _uiRandomRecipes.value = RecipesListUiState(list = recipesConverted, isLoading = false)
                }

            }
            else {
                Log.d("MainActivity", "Request Error :: ${response.exceptionOrNull()?.message.toString()}")
                val ex = response.exceptionOrNull()
                //_uiErrorFetching.value = "Some error..."
                _uiRandomRecipes.value = RecipesListUiState(isError = true, isLoading = false)
                if (ex is NetworkErrorException) {
                   // _uiErrorFetching.value = "No internet connection..."
                    _uiRandomRecipes.value = RecipesListUiState(isError = true, isLoading = false, errorMessage = "No internet connection...")
                }
            }
        }

    }

}