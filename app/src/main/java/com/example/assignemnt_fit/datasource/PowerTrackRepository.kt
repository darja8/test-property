package com.example.assignemnt_fit.datasource

import android.app.Application
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.WeekDay

class PowerTrackRepository (application: Application){
    private val exerciseDao = PowerTrackRoomDatabase.getDatabase(application)!!.exerciseDao()
    private val weekdayDao = PowerTrackRoomDatabase.getDatabase(application)!!.weekdayDao()


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

    fun getAllExercises() = exerciseDao.getAllExercises()

    fun getExercises(
        id: Int,
        name: String,
        sets: Int,
        repetitions: Int,
        weight: Int,
        duration: Int,
        dropSet: Boolean,
    ) = exerciseDao.getExercises(id,name,sets,repetitions,weight,duration,dropSet)

    suspend fun insertWeekDay(weekDay: WeekDay) {
        weekdayDao.insertWeekDay(weekDay)
    }

    suspend fun updateWeekDay(weekDay: WeekDay) {
        weekdayDao.updateWeekDay(weekDay)
    }

//    suspend fun deleteWeekDay(weekDay: WeekDay) {
//        weekdayDao.deleteWeekDay(weekDay)
//    }

    fun getAllWeekDays() = weekdayDao.getAllWeekDays()

    fun getWeekDayById(id: Int) = weekdayDao.getWeekDayById(id)

}