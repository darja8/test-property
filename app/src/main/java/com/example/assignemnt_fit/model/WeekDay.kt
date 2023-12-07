package com.example.assignemnt_fit.model

data class WeekDay(
    val day: String,
    val title: String,
    val exercises: List<Exercise> = listOf(),
    val workoutLength: Int = 10,
    val resourceId: Int){}
