package com.example.assignemnt_fit.model
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "exercise_weekday_join",
    primaryKeys = ["exerciseId", "weekDayId"],
    foreignKeys = [
        ForeignKey(entity = Exercise::class, parentColumns = ["exerciseId"], childColumns = ["exerciseId"]),
        ForeignKey(entity = WeekDay::class, parentColumns = ["weekDayId"], childColumns = ["weekDayId"])
    ]
)
data class ExerciseWeekDayJoin(
    val exerciseId: Long,
    val weekDayId: Long
)