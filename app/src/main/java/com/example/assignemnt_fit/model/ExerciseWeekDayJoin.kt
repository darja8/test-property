package com.example.assignemnt_fit.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "exerciseWeekdayJoin",
    primaryKeys = ["exerciseId", "weekDayId"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exerciseId"]),
        ForeignKey(entity = WeekDay::class, parentColumns = ["id"], childColumns = ["weekDayId"])
    ]
)
data class ExerciseWeekDayJoin(
    val exerciseId: Long,
    val dayId: Long
)
