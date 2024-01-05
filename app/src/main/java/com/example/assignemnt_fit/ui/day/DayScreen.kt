package com.example.assignemnt_fit.ui.day

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assignemnt_fit.R.*
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayViewModel
import com.example.assignemnt_fit.ui.navigation.Screen.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DayScreen(
    selectedDayId: Long,
    navController: NavHostController,
    viewModel: WeekDayViewModel
) {
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val exercisesForDay by viewModel.getExercisesForDay(selectedDayId).observeAsState(initial = emptyList())


    FloatingActionButton(onClick = { isBottomSheetOpen = true}) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)

    }

    if (isBottomSheetOpen) {
        BottomSheetScaffold(
            sheetContent = {
                // Create a Composable for the bottom sheet content
                // This can include the list of exercises and a button to add them to the selected day
                BottomSheetContent(
                    selectedDayId = selectedDayId,
                    viewModel = viewModel,
                    onCloseSheet = { isBottomSheetOpen = false }
                )
            },
            sheetPeekHeight = 0.dp, // Adjust as needed
        ) {
            // ... Rest of your DayScreen content
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedDayId: Int,
    viewModel: WeekDayViewModel,
    onCloseSheet: () -> Unit
) {
    // Fetch the list of exercises using viewModel
    val allExercises by viewModel.allExercises.observeAsState(initial = emptyList())

    LazyColumn {
        items(allExercises) { exercise ->
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(text = exercise.name, modifier = Modifier.weight(1f))
                Button(onClick = {
                    viewModel.assignExercisesToDay(selectedDayId, listOf(exercise.exerciseId))
                    onCloseSheet()
                }) {
                    Text("Add")
                }
            }
        }
    }

    Button(onClick = onCloseSheet) {
        Text(text = "Close")
    }
}

@Composable
private fun DayScreenContent(day: WeekDay, clickAction: () -> Unit){
    DayCard(
        day = day,
        modifier = Modifier
            .padding(end = 4.dp, top = 4.dp)
            .clickable(onClick = { clickAction.invoke() }),
    )
}

@Composable
fun TopAppBarTrainingDay(
    selectedDayId: Int,
    navController: NavController
){
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                Text("${dayDisplayed.day} - ${dayDisplayed.title}")
                Icon(imageVector = Icons.Filled.Create,
                    contentDescription = "",
                    modifier = Modifier.size(15.dp))
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(string.exercise_list),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },

    )
}


//@Preview(showBackground = true)
//@Composable
//fun ExerciseListScreen() {
//    val navController = rememberNavController()
//    Assignemnt_fitTheme() {
//        DayScreen(selectedDayId = 1, navController = navController)
//    }
//}