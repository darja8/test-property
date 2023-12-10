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
            false,)),10,R.drawable.workout,
        1),
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
    ),10,R.drawable.workout, 2),
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
            false)),10,R.drawable.workout,3),
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
            false)),20,R.drawable.workout,4),
    WeekDay("Friday","Cardio", listOf(
        Exercise(
            "Leg Curl",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout,5),
    WeekDay("Saturday","Legs", listOf(
        Exercise(
            "Squat",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout,6),
    WeekDay("Sunday", "Upper body", listOf(
        Exercise(
            "PullUp",
            5,
            10,
            10,
            0,
        false)),10,R.drawable.workout,7),
)