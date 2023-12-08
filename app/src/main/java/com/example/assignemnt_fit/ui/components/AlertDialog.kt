package com.example.assignemnt_fit.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

@Composable
fun AlertDialog(
    dialogIsOpen: Boolean,
    dialogOpen: (Boolean) -> Unit = {},
) {
    if(dialogIsOpen){
        AlertDialog(
            title = {
                //text to be changed
                Text(text = stringResource(id = R.string.dialog))
            },
            text = {
                //text to be changed
                Text(text = stringResource(id = R.string.dialog))
            },
            onDismissRequest = {
            },
            confirmButton = {
                TextButton(
                    onClick = {
                    }
                ) {
                    Text("Remove")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogOpen(false)
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogPreview() {
    Assignemnt_fitTheme {
        AlertDialog(
            dialogIsOpen = true,
        )
    }
}