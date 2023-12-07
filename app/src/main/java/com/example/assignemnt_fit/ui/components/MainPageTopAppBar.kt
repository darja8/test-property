package com.example.assignemnt_fit.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
//import com.example.assignemnt_fit.HomeScreenPreview
import com.example.assignemnt_fit.R
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

@Composable
fun MainPageTopAppBar(
){
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor =MaterialTheme.colorScheme.onPrimary
        ),

    )
}

@Preview
@Composable
private fun MainPageTopAppBarPreview() {
    Assignemnt_fitTheme(dynamicColor = false) {
        MainPageTopAppBar()
    }
}