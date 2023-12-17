package com.example.assignemnt_fit

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignemnt_fit.ui.day.DayScreen
import com.example.assignemnt_fit.ui.exercises.ExerciseListScreen
import com.example.assignemnt_fit.ui.navigation.Screen


import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Assignemnt_fitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}

@Composable
private fun BuildNavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController, Color.White) }
        composable(Screen.Exercises.route) { ExerciseListScreen(navController) }
        composable(
            route = "${Screen.Day.route}/{dayId}",
            arguments = listOf(navArgument("dayId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            DayScreen(navBackStackEntry.arguments!!.getInt("dayId"), navController)
        }
    }
}
//
//var day: WeekDay = TODO()
//fun SetDay(chosenDay: WeekDay){
//    day = chosenDay
//}
//
//fun GetDay(): WeekDay {
//    return day
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignemnt_fitTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}