package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeekDayDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertAll(weekDays: List<WeekDay>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weekDays: List<WeekDay>)

    @Query("SELECT * FROM weekDays")
    fun getAllWeekDays(): LiveData<List<WeekDay>>

    @Query("SELECT * FROM WeekDays WHERE weekDayId = :dayId")
    fun getDayById(dayId: Long): LiveData<WeekDay>

}