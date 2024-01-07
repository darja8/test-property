package com.example.assignemnt_fit.ui.exercises

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun DropSetDialog(
    dropSets: List<DropSetEntry>,
    onDismiss: () -> Unit,
    onConfirm: (List<DropSetEntry>) -> Unit
) {
    var localDropSets by remember { mutableStateOf(dropSets) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Drop Set Details") },
        text = {
            Column {
                localDropSets.forEachIndexed { index, dropSet ->
                    DropSetEntryRow(
                        dropSet = dropSet,
                        onUpdate = { updatedDropSet ->
                            localDropSets = localDropSets.toMutableList().apply {
                                this[index] = updatedDropSet
                            }
                        }
                    )
                }
                Button(onClick = { localDropSets += DropSetEntry(0, 0) }) {
                    Text("Add Drop Set")
                }
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(localDropSets) }) {
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


@Composable
fun DropSetEntryRow(dropSet: DropSetEntry, onUpdate: (DropSetEntry) -> Unit) {
    var weight by remember { mutableStateOf(dropSet.weight.toString()) }
    var reps by remember { mutableStateOf(dropSet.repetitions.toString()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
//        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = reps,
            onValueChange = { reps = it },
            label = { Text("Reps") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
//        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onUpdate(DropSetEntry(weight.toIntOrNull() ?: 0, reps.toIntOrNull() ?: 0)) }) {
            Text("Update")
        }
    }
}

data class DropSetEntry(val weight: Int, val repetitions: Int)
