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
    @Query("SELECT * FROM exercises WHERE exerciseId IN (SELECT exerciseId FROM exercise_weekday_join WHERE weekDayId = :weekDayId)")
    fun getExercisesForWeekDay(weekDayId: Int): LiveData<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM WeekDays WHERE weekDayId IN (SELECT weekDayId FROM exercise_weekday_join WHERE exerciseId = :exerciseId)")
    fun getWeekDaysForExercise(exerciseId: Int): LiveData<List<WeekDay>>
}
