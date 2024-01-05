package com.example.assignemnt_fit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("DELETE FROM exercises")
    suspend fun clearTable()

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): LiveData<List<Exercise>>
    @Insert
    suspend fun insertExerciseWeekDayJoin(join: ExerciseWeekDayJoin)

    @Query("""SELECT * FROM exercises WHERE exerciseId = :id AND exercise_name = :name AND sets = :sets AND repetitions = :repetitions AND weight = :weight AND duration = :duration AND dropSet = :dropSet""")
    fun getExercises(
        id: Long,
        name: String,
        sets: Int,
        repetitions: Int,
        weight: Int,
        duration: Int,
        dropSet: Boolean,
    ): LiveData<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercises INNER JOIN ExerciseWeekDayJoin ON exercises.exerciseId = ExerciseWeekDayJoin.exerciseId WHERE ExerciseWeekDayJoin.dayId = :dayId")
    fun getExercisesForDay(dayId: Long): LiveData<List<Exercise>>

    @Insert
    suspend fun insertWeekDay(weekDay: WeekDay)

}