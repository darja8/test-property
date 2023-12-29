package com.example.assignemnt_fit.model

import androidx.room.Entity

@Entity(tableName = "weekdays")
data class WeekDay(
    val dayName: String,
    val trainingTitle: String,
    val exercisesOnTheDay: List<Exercise> = listOf(),
    val workoutLengthInMinutes: Int = 0,
    val dayId: Int
)


