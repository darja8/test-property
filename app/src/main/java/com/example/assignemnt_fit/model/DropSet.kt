package com.example.assignemnt_fit.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "drop_sets",
    foreignKeys = [ForeignKey(
        entity = Exercise::class,
        parentColumns = arrayOf("exerciseId"),
        childColumns = arrayOf("exerciseId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DropSet(
    @PrimaryKey(autoGenerate = true) val dropSetId: Long = 0,
    val exerciseId: Long, // Link to the Exercise table
    val weight: Int, // Weight for this drop set
    val repetitions: Int // Reps for this drop set
)