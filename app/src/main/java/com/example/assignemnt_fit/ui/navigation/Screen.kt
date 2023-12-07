package com.example.assignemnt_fit.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Exercises : Screen("exercises")
}

/**
 * List of top-lvel screens provided as a convenience.
 */
val screens = listOf(
    Screen.Home,
    Screen.Exercises
)