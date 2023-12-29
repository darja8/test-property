package com.example.assignemnt_fit.ui.components
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubpageScaffold(
    navController: NavHostController,
    title: String,
    floatingActionButton: @Composable () -> Unit = {},
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            SubpageTopAppBar(navController, title)
        },
        floatingActionButton = floatingActionButton,

        content = { innerPadding ->
            pageContent(innerPadding)
        }
    )
}
