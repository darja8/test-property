package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ExerciseWeekDayJoinDao {
    @Insert
    suspend fun insert(join: ExerciseWeekDayJoin)

    @Transaction
    @Query("SELECT * FROM exercises WHERE id IN (SELECT exerciseId FROM exerciseWeekdayJoin WHERE weekDayId = :weekDayId)")
    fun getExercisesForWeekDay(weekDayId: Int): LiveData<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM weekDays WHERE id IN (SELECT weekDayId FROM exerciseWeekdayJoin WHERE exerciseId = :exerciseId)")
    fun getWeekDaysForExercise(exerciseId: Int): LiveData<List<WeekDay>>

    companion object {
        fun insertAll(toTypedArray: Array<ExerciseWeekDayJoin>) {

        }
    }
}
