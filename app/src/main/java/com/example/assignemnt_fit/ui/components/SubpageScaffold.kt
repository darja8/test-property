package com.example.assignemnt_fit.ui.components
import com.example.assignemnt_fit.ui.components.AlertDialog
import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubpageScaffold(
    navController: NavHostController,
    floatingActionButton: @Composable () -> Unit = {},
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            SubpageTopAppBar(navController)
        },
        floatingActionButton = floatingActionButton,

        content = { innerPadding ->
            pageContent(innerPadding)
        }
    )
}
