package com.example.assignemnt_fit.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExerciseDao
import com.example.assignemnt_fit.model.ExerciseWeekDayJoin
import com.example.assignemnt_fit.model.ExerciseWeekDayJoinDao
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayDao

class PowerTrackRepository (application: Application){
    private val exerciseDao: ExerciseDao
    private val weekDayDao: WeekDayDao
    private val exerciseWeekDayJoinDao: ExerciseWeekDayJoinDao

    init {
        val db = PowerTrackRoomDatabase.getDatabase(application)
        exerciseDao = db.exerciseDao()
        weekDayDao = db.weekdayDao()
        exerciseWeekDayJoinDao = db.exerciseWeekDayJoinDao()
    }


    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()
    val allWeekDays: LiveData<List<WeekDay>> = weekDayDao.getAllWeekDays()


    /**
     * Exercises
     */
    suspend fun insert(exercise: Exercise){
        exerciseDao.insertExercise(exercise)
    }
    suspend fun clearTable() {
        exerciseDao.clearTable()
    }

    suspend fun deleteExercise(exercise: Exercise) {
        exerciseDao.deleteExercise((exercise))
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDao.updateExercise(exercise)
    }

    fun fetchAllExercises() = exerciseDao.getAllExercises()

    fun getExercises(
        id: Long,
        name: String,
        sets: Int,
        repetitions: Int,
        weight: Int,
        duration: Int,
        dropSet: Boolean,
    ) = exerciseDao.getExercises(id,name,sets,repetitions,weight,duration,dropSet)

    /**
     * WeekDays
     */

    fun getDayById(dayId: Long): LiveData<WeekDay> {
        return weekDayDao.getDayById(dayId)
    }
    suspend fun insertWeekDays(weekDays: List<WeekDay>){
        weekDayDao.insertAll(weekDays)
    }
    /**
     * Exercise and WeekDay join
     */
    fun insertWeekDay(weekDay: WeekDay) {
    }

    suspend fun assignExerciseToDay(exerciseId: Int, weekDayId: Int) {
        val join = ExerciseWeekDayJoin(exerciseId, weekDayId)
        exerciseWeekDayJoinDao.insert(join)
    }

    fun getExercisesForWeekDay(weekDayId: Int): LiveData<List<Exercise>> {
        return exerciseWeekDayJoinDao.getExercisesForWeekDay(weekDayId)
    }

    fun getWeekDaysForExercise(exerciseId: Int): LiveData<List<WeekDay>> {
        return exerciseWeekDayJoinDao.getWeekDaysForExercise(exerciseId)
    }

}