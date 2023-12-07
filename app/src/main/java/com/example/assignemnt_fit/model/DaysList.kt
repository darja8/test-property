package com.example.assignemnt_fit.model

import com.example.assignemnt_fit.R


val days = listOf(
    WeekDay("Monday", "Legs", listOf(
        Exercise(
            "PushUp",
            5,
            10,
            10,
            0,
        false),
        Exercise(
            "PullUp",
            5,
            10,
            10,
            0,
            false),
        Exercise(
            "Bench Press",
            5,
            10,
            10,
            0,
            false)),10,R.drawable.workout),
    WeekDay("Tuesday","Upper Body", listOf(
        Exercise("Leg Curl",
            5,
            10,
            10,
            0,
            false),
        Exercise("Squat",
            5,
            10,
            10,
            0,
            false)
    ),10,R.drawable.workout),
    WeekDay("Wednesday", "ABS",listOf(
        Exercise(
            "PushUp",
            5,
            10,
            10,
            0,
            false),
        Exercise(
            "PullUp",
            5,
            10,
            10,
            0,
            false),
        Exercise(
            "Bench Press",
            5,
            10,
            10,
            0,
            false)),10,R.drawable.workout),
    WeekDay("Thursday", "Arms",listOf(
        Exercise(
            "PushUp",
            5,
            10,
            10,
            0,
            false),
        Exercise(
            "PullUp",
            5,
            10,
            10,
            0,
            false),
        Exercise(
            "Bench Press",
            5,
            10,
            10,
            0,
            false)),20,R.drawable.workout),
    WeekDay("Friday","Cardio", listOf(
        Exercise(
            "Leg Curl",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout),
    WeekDay("Saturday","Legs", listOf(
        Exercise(
            "Squat",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout),
    WeekDay("Sunday", "Upper body", listOf(
        Exercise(
            "PullUp",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout),
)