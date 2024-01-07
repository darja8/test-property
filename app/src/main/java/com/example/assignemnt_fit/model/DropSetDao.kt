package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DropSetDao {
    @Insert
    suspend fun insertDropSet(dropSet: DropSet): Long

    @Update
    suspend fun updateDropSet(dropSet: DropSet)

    @Query("SELECT * FROM drop_sets WHERE exerciseId = :exerciseId")
    fun getDropSetsForExercise(exerciseId: Long): LiveData<List<DropSet>>
}
