package com.example.assignemnt_fit.ui.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.model.Exercise


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExerciseItem(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    onClick: () -> Unit = {},
    onDelete: (Exercise) -> Unit,
    onEdit: (Long) -> Unit,
//    navController: NavController
) {
    var dialogIsOpen by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.workout),
                contentDescription = null, // Provide a meaningful content description
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(shape = RectangleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Text displaying the exercise name
            Column {
                Text(text = exercise.name)
                Text(text = "${exercise.sets} sets, ${exercise.repetitions} reps")
                if (exercise.dropSet){
                    Text(text = "drop-set")
                }

            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = { onEdit(exercise.exerciseId) },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.exercise_list)
                )
            }
            IconButton(
                onClick = { dialogIsOpen = true},
                modifier = Modifier.padding(0.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.exercise_list)
                )
            }
            if (dialogIsOpen){
                DeleteConfirmationDialog(
                    onConfirm = {
                        dialogIsOpen = false
                        onDelete(exercise) },
                    onDismiss = { dialogIsOpen = false }
                )
            }
        }

    }
}

@Composable
fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        title = { Text(text = "Are you sure?") },
        text = { Text(text = "Once removed from this list it will no longer be available") },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text("Remove")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Cancel")
            }
        }
    )
}
