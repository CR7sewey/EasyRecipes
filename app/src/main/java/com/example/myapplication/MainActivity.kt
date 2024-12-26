package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.randomList.presentation.RandomRecipesViewModel
import com.example.myapplication.recipeDetails.presentation.RecipeDetailsViewModel
import com.example.myapplication.searchedRecipes.presentation.SearchedRecipesViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val randomRecipesVM by viewModels<RandomRecipesViewModel> { RandomRecipesViewModel.Factory }
    private val recipeDetailsVM by viewModels<RecipeDetailsViewModel> { RecipeDetailsViewModel.Factory }
    private val recipesSearchedVM by viewModels<SearchedRecipesViewModel> { SearchedRecipesViewModel.Factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(randomRecipesVM, recipeDetailsVM, recipesSearchedVM)
                }
            }
        }
    }
}
