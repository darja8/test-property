package com.example.assignemnt_fit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeekDays")
data class WeekDay(
    @PrimaryKey(autoGenerate = true) val dayId: Long = 0,
    val dayName: String,
    val trainingTitle: String,
//    val exercisesOnTheDay: List<Int> = listOf(),
    val workoutLengthInMinutes: Int = 0,
//    val dayId: Int
)


