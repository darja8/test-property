package com.example.assignemnt_fit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.days
import com.example.assignemnt_fit.model.exercises
import com.example.assignemnt_fit.ui.theme.Assignemnt_fitTheme


@Composable
fun ExerciseCard() {
    // Row to display a person's information
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Icon representing a person
//        Icon(
//            imageVector = Icons.Default.Person,
//            contentDescription = null,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .background(MaterialTheme.colorScheme.primary)
//                .padding(8.dp)
//        )
        // Spacer to add space between icon and text
//        Spacer(modifier = Modifier.width(16.dp))
//        // Text displaying the person's name
//        Text(text = person.name, style = MaterialTheme.typography.body1)
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