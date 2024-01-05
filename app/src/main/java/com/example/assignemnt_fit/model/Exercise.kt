package com.example.assignemnt_fit.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long = 0,
    @ColumnInfo(name = "exercise_name")
    var name: String,
    var sets: Int,
    var repetitions: Int,
    var weight: Int,
    var duration: Int,
    var dropSet: Boolean,
)