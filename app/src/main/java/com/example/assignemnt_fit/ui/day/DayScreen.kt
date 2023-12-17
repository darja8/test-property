package com.example.assignemnt_fit.ui.day

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import com.example.assignemnt_fit.R.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignemnt_fit.model.days
import com.example.assignemnt_fit.ui.components.MainPageTopAppBar
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme
import com.example.assignemnt_fit.ui.navigation.Screen.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DayScreen(
    selectedDayId: Int,
    navController: NavHostController
) {
    val dayDisplayed = days.first { dayDisplayed -> selectedDayId == dayDisplayed.dayId}
    Scaffold {
        Column {
            TopAppBarTrainingDay(selectedDayId, navController = navController)
//            MainPageTopAppBar()
            LazyColumn {
                items(dayDisplayed.exercises) { exercise ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = drawable.workout), // Assuming photoResId is the resource ID of the photo
                            contentDescription = null, // Provide a meaningful content description
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .clip(shape = RectangleShape)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = exercise.name)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Sets: ${exercise.sets}, Reps: ${exercise.repetitions}")
                            Spacer(modifier = Modifier.height(8.dp))
                            // Add more details as needed
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarTrainingDay(
    selectedDayId: Int,
    navController: NavController
){
    val dayDisplayed = days.first { dayDisplayed -> selectedDayId == dayDisplayed.dayId}

    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("${dayDisplayed.day} - ${dayDisplayed.title}")
                Icon(imageVector = Icons.Filled.Create,
                    contentDescription = "",
                    modifier = Modifier.size(15.dp))
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(string.exercise_list),
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },

    )
}


@Preview(showBackground = true)
@Composable
fun ExerciseListScreen() {
    val navController = rememberNavController()
    Assignemnt_fitTheme() {
        DayScreen(selectedDayId = days.first().dayId, navController)
    }
}