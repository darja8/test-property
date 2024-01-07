package com.example.assignemnt_fit

import AddNewExerciseScreen
import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignemnt_fit.model.ExercisesViewModel
import com.example.assignemnt_fit.model.WeekDayViewModel
import com.example.assignemnt_fit.ui.day.DayScreen
import com.example.assignemnt_fit.ui.exercises.ExerciseListScreen
import com.example.assignemnt_fit.ui.navigation.Screen

@Composable
fun BuildNavigationGraph(
    exercisesViewModel: ExercisesViewModel = viewModel(),
    weekDayViewModel: WeekDayViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController, Color.White) }
        composable(Screen.Exercises.route) { ExerciseListScreen(navController,exercisesViewModel) }
        composable(
            route = "${Screen.Day.route}/{dayId}",
            arguments = listOf(navArgument("dayId") {
                type = NavType.LongType
            })
        ) { navBackStackEntry ->
            DayScreen(navBackStackEntry.arguments!!.getLong("dayId"), navController, weekDayViewModel, exercisesViewModel)
        }
        composable(
            route = "newExercise/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") {
                type = NavType.LongType
                defaultValue = -1 // Default value for new exercise
            })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getLong("exerciseId")
            AddNewExerciseScreen(navController, exerciseId, exercisesViewModel)
        }

    }
}