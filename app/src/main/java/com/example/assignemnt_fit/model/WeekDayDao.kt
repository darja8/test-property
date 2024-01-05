package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeekDayDao {
    @Insert
    suspend fun insertAll(vararg weekDays: WeekDay)

    @Query("SELECT * FROM weekDays")
    fun getAllWeekDays(): LiveData<List<WeekDay>>

//    @Query("SELECT * FROM weekdays")
//    suspend fun getAllWeekDays(): List<WeekDay>

}