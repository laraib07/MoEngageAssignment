package com.laraib07.moengageassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laraib07.moengageassignment.ui.screen.HomeScreen
import com.laraib07.moengageassignment.ui.screen.NewsScreen

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.NewsScreen.route) {
            NewsScreen()
        }
    }
}