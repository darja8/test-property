package com.example.assignemnt_fit.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Remove")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    val navController = rememberNavController()
    Assignemnt_fitTheme {
        com.example.assignemnt_fit.ui.components.AlertDialog(
            onDismissRequest = { /*TODO*/ },
            onConfirmation = { /*TODO*/ },
            dialogTitle = "Test",
            dialogText = "Test"
        )
    }
}