package com.example.assignemnt_fit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignemnt_fit.datasource.PowerTrackRoomDatabase
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PowerTrackRoomDatabase.getDatabase(this)
        setContent {
            Assignemnt_fitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Assignemnt_fitTheme(dynamicColor = false) {
        BuildNavigationGraph()
    }
}