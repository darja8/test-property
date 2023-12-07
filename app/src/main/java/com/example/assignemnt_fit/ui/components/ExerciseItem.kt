package com.example.assignemnt_fit.ui.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    deleteExercise: (Exercise) -> Unit = {},
//    onDeleteClick: () -> Unit, // Add onDeleteClick as a parameter
    onClick: () -> Unit = {}
)
{
    val openAlertDialog = remember { mutableStateOf(false) }

    // Row to display an exercise's information
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
                painter = painterResource(id = R.drawable.workout), // Assuming photoResId is the resource ID of the photo
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
                Text(text = "${exercise.sets.toString()} sets, ${exercise.repetitions} reps")
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = {
                    onClick
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.exercise_list)
                )
            }
            IconButton(
                onClick = { onClick },
                modifier = Modifier.padding(0.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.exercise_list)
                )
            }
        }
    }
}