package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.randomList.presentation.RandomRecipesViewModel
import com.example.myapplication.randomList.presentation.ui.RandomRecipesScreen
import com.example.myapplication.recipeDetails.presentation.RecipeDetailsViewModel
import com.example.myapplication.recipeDetails.presentation.ui.RecipeDetailsScreen
import com.example.myapplication.searchedRecipes.presentation.SearchedRecipesViewModel
import com.example.myapplication.searchedRecipes.presentation.ui.SearchRecipesScreen

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "entryApp") {
        composable(route = "entryApp") {
            EntryScreen(
                navController,
                modifier = modifier
            )
        }

        composable(route = "randomListRecipes") {
            RandomRecipesScreen(
                navController,
                modifier
            )
        }

        composable(route = "recipe_detail"+ "/{id}", arguments = listOf(navArgument("id"){ type=
            NavType.StringType})) { backStateEntry ->
            RecipeDetailsScreen(
                requireNotNull(
                    backStateEntry.arguments?.getString("id")?.toString()
                ), navController
            )
        }

        composable(route = "search_recipes"+ "/{query}", arguments = listOf(navArgument("query"){ type=
            NavType.StringType})) { backStateEntry ->
            SearchRecipesScreen(
                requireNotNull(
                    backStateEntry.arguments?.getString("query")?.toString()
                ), navController
            )
        }
    }
}