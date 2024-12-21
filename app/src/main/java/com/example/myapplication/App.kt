package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
    }
}