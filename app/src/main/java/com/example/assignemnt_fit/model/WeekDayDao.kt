package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeekDayDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertAll(weekDays: List<WeekDay>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weekDays: List<WeekDay>)

    @Query("SELECT * FROM weekDays")
    fun getAllWeekDays(): LiveData<List<WeekDay>>

    @Query("SELECT * FROM WeekDays WHERE weekDayId = :weekDayId")
    fun getDaysById(weekDayId: Long): LiveData<WeekDay>

    @Query("SELECT * FROM WeekDays WHERE weekDayId = :weekdayId")
    suspend fun getDayById(weekdayId: Long): WeekDay?

    @Update
    suspend fun updateWeekDay(weekDay: WeekDay)
}
