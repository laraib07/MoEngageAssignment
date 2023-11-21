package com.laraib07.moengageassignment.ui.navigation

sealed class Screen (val route: String) {
    object HomeScreen : Screen("home_screen")
    object NewsScreen : Screen("news_screen")
}