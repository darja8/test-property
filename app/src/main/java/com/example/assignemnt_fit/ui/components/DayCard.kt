package com.example.assignemnt_fit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.days
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme

@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    day: WeekDay,
){
    Card(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.Center,

            modifier = Modifier
                .padding(start = 10.dp, bottom = 25.dp, top = 25.dp)

        ) {
                Text(
                    text = ("${day.day} - ${day.title}"),
                    fontSize = 20.sp,
                    modifier = Modifier
                )

                Text(
                    text = ("approx. time ${day.workoutLength.toString()} min"),
                    fontSize = 15.sp,
                    modifier = Modifier
                )
        }
    }
}

@Preview
@Composable
private fun CatCardPreview(){
    Assignemnt_fitTheme(darkTheme = true,
        dynamicColor = false) {
        DayCard(day = days[0])
    }
}