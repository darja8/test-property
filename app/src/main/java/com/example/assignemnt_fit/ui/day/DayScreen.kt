package com.example.assignemnt_fit.ui.day

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.R.*
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExercisesViewModel
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayViewModel
import com.example.assignemnt_fit.ui.navigation.Screen.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DayScreen(
    selectedDayId: Long,
    navController: NavHostController,
    viewModelWeekDay: WeekDayViewModel,
    viewModelExercise: ExercisesViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val showBottomSheet = remember { mutableStateOf(false) }
    val day by viewModelWeekDay.day.observeAsState()
    val showCustomBottomSheet = remember { mutableStateOf(false) }
    val exercises by viewModelExercise.allExercises.observeAsState(initial = emptyList())
    val exercisesForDay by viewModelWeekDay.getExercisesForDay(selectedDayId).observeAsState(initial = emptyList())
    var showEditDialog by remember { mutableStateOf(false) }


    LaunchedEffect(selectedDayId) {
        viewModelWeekDay.loadDayById(selectedDayId)
        viewModelWeekDay.loadExercisesForDay(selectedDayId)
    }

    Scaffold(
        topBar = {
            if (day != null) {
                TopAppBarTrainingDay(
                    selectedDayId = selectedDayId,
                    navController = navController,
                    day = day!!,
                    onAddExerciseClick = { showCustomBottomSheet.value = true },
                    onEditTitleClick = {showEditDialog = true}
                )
            }
        }
    ) { paddingValues ->
        Scaffold()
        { paddingValues ->
            if (exercisesForDay.isEmpty()) {
                // Display an image when there are no exercises for the day
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.no_training), // Replace with your image resource
                        contentDescription = "No Exercises",
                        modifier = Modifier.size(width = 300.dp, height = 300.dp)
                    )
//                    Text(text = "No exercises added")
                }
            } else {
                LazyColumn(modifier = Modifier.padding(top = 60.dp)) {
                    items(exercisesForDay) { exercise ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Exercise details
                            Column {
                                Text(text = exercise.name)
                                Text(text = "${exercise.sets} sets, ${exercise.repetitions} reps")
                            }

                            // Remove exercise button
                            IconButton(onClick = {
                                viewModelWeekDay.removeExerciseFromDay(exercise.exerciseId, selectedDayId)
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove exercise")
                            }
                        }
                        Divider()
                    }
                }

            }
        }
    }

    if (showCustomBottomSheet.value) {
        CustomBottomSheet(
            onDismissRequest = { showCustomBottomSheet.value = false },
            exercises = exercises,
            onConfirmSelection = { selectedExercises ->
                selectedExercises.forEach { exercise ->
                    viewModelWeekDay.assignExerciseToDay(exercise.exerciseId, selectedDayId)
                }
                // Trigger UI update
                viewModelWeekDay.getExercisesForDay(selectedDayId)
            }
        )
    }
    if (showEditDialog && day != null) {
        EditTrainingTitleDialog(
            currentTitle = day!!.trainingTitle,
            onDismiss = { showEditDialog = false },
            onConfirm = { newTitle ->
                viewModelWeekDay.updateTrainingTitle(day!!.weekDayId, newTitle)
                showEditDialog = false
            }
        )
    }

}

@Composable
fun CustomBottomSheet(
    onDismissRequest: () -> Unit,
    exercises: List<Exercise>,
    onConfirmSelection: (List<Exercise>) -> Unit // New parameter
) {
    val selectedExercises = remember { mutableStateMapOf<Long, Boolean>() }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Select Exercises") },
        text = {
            LazyColumn {
                items(exercises) { exercise ->
                    ExerciseListItem(
                        exercise = exercise,
                        isSelected = selectedExercises[exercise.exerciseId] ?: false,
                        onSelectionChanged = { selected ->
                            selectedExercises[exercise.exerciseId] = selected
                        }
                    )
                    Divider()
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onConfirmSelection(exercises.filter { selectedExercises[it.exerciseId] == true })
                onDismissRequest()
            }) {
                Text("Confirm")
            }
        }
    )
}


@Composable
fun TopAppBarTrainingDay(
    selectedDayId: Long,
    navController: NavController,
    day: WeekDay,
    onAddExerciseClick: () -> Unit,
    onEditTitleClick: () -> Unit

){
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("${day!!.dayName} - ${day!!.trainingTitle}")
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "Edit Title",
                    modifier = Modifier
                        .size(15.dp)
                        .clickable(onClick = onEditTitleClick) // Call onEditTitleClick here
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(string.exercise_list),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 4.dp)
            ) {
                IconButton(onClick = onAddExerciseClick) { // Use the lambda here
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Exercise",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
    )
}

@Composable
private fun ExerciseListItem(
    exercise: Exercise,
    isSelected: Boolean,
    onSelectionChanged: (Boolean) -> Unit
)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable.workout),
                contentDescription = null, // Provide a meaningful content description
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(shape = RectangleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = exercise.name)
                Text(text = "${exercise.sets} sets, ${exercise.repetitions} reps")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isSelected,
                onCheckedChange = onSelectionChanged,
            )
        }

    }
}

@Composable
fun EditTrainingTitleDialog(
    currentTitle: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf(currentTitle) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Training Title") },
        text = {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Training Title") }
            )
        },
        confirmButton = {
            Button(onClick = { onConfirm(text) }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
