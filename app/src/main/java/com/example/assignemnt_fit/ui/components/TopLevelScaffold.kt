package com.example.assignemnt_fit.ui.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

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
