package com.example.assignemnt_fit.ui.day

//import com.example.assignemnt_fit.model.days
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignemnt_fit.model.WeekDay

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
                    text = buildString {
                        append(day.dayName)
                        if (day.trainingTitle.isNotBlank()) {
                            append(" - ${day.trainingTitle}")
                        } else {
                            append(" - Day Off")
                        }
                    },
                    fontSize = 20.sp,
                    modifier = Modifier
                )

                Text(
                    text = if (day.workoutLengthInMinutes != null && day.workoutLengthInMinutes != 0) {
                        "approx. time ${day.workoutLengthInMinutes} min"
                    } else {
                        ""
                    },
                    fontSize = 15.sp,
                    modifier = Modifier
                )
        }
    }
}

@Preview
@Composable
private fun CatCardPreview(){
//    Assignemnt_fitTheme(darkTheme = true,
//        dynamicColor = false) {
//        DayCard(day = days[0])
//    }
}