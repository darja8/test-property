package com.example.assignemnt_fit.ui.day

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import com.example.assignemnt_fit.R.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.model.days
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme
import com.example.assignemnt_fit.ui.navigation.Screen.*
import com.example.assignemnt_fit.ui.components.SubpageTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DayScreen(
    selectedDayId: Int,
    navController: NavHostController
) {
    val dayDisplayed = days.first { dayDisplayed -> selectedDayId == dayDisplayed.dayId}
    Scaffold {
        Column {
            SubpageTopAppBar(navController = navController)
            // Display the list of exercises using LazyColumn
            LazyColumn {
                items(dayDisplayed.exercises) { exercise ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = exercise.name)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Sets: ${exercise.sets}, Reps: ${exercise.repetitions}")
                            Spacer(modifier = Modifier.height(8.dp))
                            // Add more details as needed
                        }
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseListScreen() {
    val navController = rememberNavController()
    Assignemnt_fitTheme(dynamicColor = false) {
        DayScreen(selectedDayId = days.first().dayId, navController)
    }
}