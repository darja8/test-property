package com.example.assignemnt_fit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

@Composable
fun SubpageTopAppBar(
    navController: NavController,
    title: String
){
    TopAppBar(
        title = {
            Column {
                Text(title)
//                Text("title")
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
                    contentDescription = stringResource(R.string.exercise_list)
                )
            }
        },
    )
}

@Preview
@Composable
private fun MainPageTopAppBarPreview() {
    Assignemnt_fitTheme(dynamicColor = false) {
        SubpageTopAppBar(
            navController = rememberNavController(),
            title = "Test"
        )
    }
}