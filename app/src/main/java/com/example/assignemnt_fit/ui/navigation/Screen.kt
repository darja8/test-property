package com.example.assignemnt_fit.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Exercises : Screen("exercises")
    object Day : Screen("day")
}

/**
 * List of top-level screens provided as a convenience.
 */

val screens = listOf(
    Screen.Home,
    Screen.Exercises,
)