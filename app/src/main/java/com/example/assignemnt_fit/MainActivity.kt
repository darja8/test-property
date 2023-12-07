package com.example.assignemnt_fit

import ExerciseScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable(Screen.Home.route) { ExerciseScreen(navController) }
        composable(Screen.Exercises.route) { ExerciseListScreen(navController)}
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignemnt_fitTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}