package com.example.assignemnt_fit.ui.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.model.ExercisesViewModel
import com.example.assignemnt_fit.ui.components.SubpageScaffold
import com.example.assignemnt_fit.ui.navigation.Screen.NewExercise


@Composable
fun ExerciseListScreen(
    navController: NavHostController,
    exercisesViewModel: ExercisesViewModel = viewModel()
) {
    val exercises by exercisesViewModel.allExercises.observeAsState(listOf())

    SubpageScaffold(
        navController = navController,
        title = "Exercises",
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(NewExercise.route)},
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.exercise_list))
            }
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
//            val exercises = listOf<Exercise>()
//            if (exercises.isEmpty()) {
//                Text(
//                    text = "No exercises",
//                    fontSize = 20.sp,
//                    modifier = Modifier.padding(16.dp)
//                )
//            } else {
                LazyColumn {
                    items(exercises) { exercise ->
                        ExerciseItem(
                            exercise = exercise,
                            onDelete = { exercisesViewModel.deleteExercise(it) }
                        )
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                        )
                    }
                }
        }
    }
}
