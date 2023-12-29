package com.example.assignemnt_fit.model

import androidx.annotation.NonNull
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.listSaver
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    @ColumnInfo(name = "exercise_name")
    var name: String,
    var sets: Int,
    var repetitions: Int,
    var weight: Int,
    var duration: Int,
    var dropSet: MutableState<Boolean>,
)

val ExerciseSaver = listSaver<Exercise, Any>(
    save = { listOf(it.id, it.name, it.sets, it.repetitions, it.weight, it.duration, it.dropSet) },
    restore = {
        Exercise(
            id = it[0] as Int,
            name = it[1] as String,
            sets = it[2] as Int,
            repetitions = it[3] as Int,
            weight = it[4] as Int,
            duration = it[5] as Int,
            dropSet = it[6] as MutableState<Boolean>
        )
    }
)