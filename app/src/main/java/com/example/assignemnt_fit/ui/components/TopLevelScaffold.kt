package com.example.assignemnt_fit.ui.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLevelScaffold(
    navController: NavHostController,
    floatingActionButton: @Composable () -> Unit = { },
    coroutineScope: CoroutineScope,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
        Scaffold(
            topBar = {
                MainPageTopAppBar()
            },
            floatingActionButton = floatingActionButton,
            content = { innerPadding ->
                pageContent(innerPadding)
            }
        )
}