package com.example.assignemnt_fit.ui.exercises

//import uk.ac.aber.dcs.cs31620.faa.ui.components.DefaultSnackbar
import androidx.compose.foundation.background
import com.example.assignemnt_fit.R.*
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.exercises
import com.example.assignemnt_fit.ui.components.AlertDialog
import com.example.assignemnt_fit.ui.components.SubpageScaffold
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme
import com.example.assignemnt_fit.ui.navigation.Screen.*
import com.example.assignemnt_fit.ui.components.ExerciseItem

@Composable
fun ExerciseListScreen(
    navController: NavHostController
) {

    SubpageScaffold(
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.exercise_list)                )
            }
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            // Display the list of exercises using LazyColumn
            LazyColumn {
                items(exercises) { exercise ->

                    ExerciseItem(exercise = exercise,
                        onClick = {
//                            exerciseToDelete = exercise
                        }
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


@Preview(showBackground = true)
@Composable
fun ExerciseListScreen() {
    val navController = rememberNavController()
    Assignemnt_fitTheme(dynamicColor = false) {
        ExerciseListScreen(navController)
    }
}