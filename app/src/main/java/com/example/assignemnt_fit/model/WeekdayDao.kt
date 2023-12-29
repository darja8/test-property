package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeekdayDao {

    @Insert()
    suspend fun insertWeekDay(weekDay: WeekDay)

    @Update
    suspend fun updateWeekDay(weekDay: WeekDay)

    @Delete
    suspend fun deleteWeekDay(weekDay: WeekDay)

    @Query("SELECT * FROM weekdays")
    fun getAllWeekDays(): LiveData<List<WeekDay>>

    @Query("SELECT * FROM weekdays WHERE dayId = :dayId")
    fun getWeekDayById(dayId: Int): LiveData<WeekDay>

}