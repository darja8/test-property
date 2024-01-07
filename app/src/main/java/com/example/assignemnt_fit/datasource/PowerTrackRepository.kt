package com.example.assignemnt_fit.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.assignemnt_fit.model.DropSet
import com.example.assignemnt_fit.model.DropSetDao
import com.example.assignemnt_fit.model.Exercise
import com.example.assignemnt_fit.model.ExerciseDao
import com.example.assignemnt_fit.model.ExerciseWeekDayJoin
import com.example.assignemnt_fit.model.ExerciseWeekDayJoinDao
import com.example.assignemnt_fit.model.WeekDay
import com.example.assignemnt_fit.model.WeekDayDao

import com.example.assignemnt_fit.ui.exercises.DropSetEntry

class PowerTrackRepository (application: Application){
    private val exerciseDao: ExerciseDao
    private val weekDayDao: WeekDayDao
    private val exerciseWeekDayJoinDao: ExerciseWeekDayJoinDao
    private val dropSetDao: DropSetDao

    init {
        val db = PowerTrackRoomDatabase.getDatabase(application)
        exerciseDao = db.exerciseDao()
        weekDayDao = db.weekdayDao()
        exerciseWeekDayJoinDao = db.exerciseWeekDayJoinDao()
        dropSetDao = db.dropSetDao()
    }


    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()
    val allWeekDays: LiveData<List<WeekDay>> = weekDayDao.getAllWeekDays()


    /**
     * Exercises
     */
//    suspend fun insert(exercise: Exercise){
//        exerciseDao.insertExercise(exercise)
//    }
    suspend fun insert(exercise: Exercise): Long {
        return exerciseDao.insertExercise(exercise)
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

    fun getDaysById(weekdayId: Long): LiveData<WeekDay> {
        return weekDayDao.getDaysById(weekdayId)
    }

    suspend fun getDayById(weekdayId: Long): WeekDay? {
        return weekDayDao.getDayById(weekdayId)
    }

    suspend fun insertWeekDays(weekDays: List<WeekDay>){
        weekDayDao.insertAll(weekDays)
    }
    suspend fun updateWeekDay(weekDay: WeekDay) {
        weekDayDao.updateWeekDay(weekDay)
    }

    /**
     * Exercise and WeekDay join
     */
    fun insertWeekDay(weekDay: WeekDay) {
    }

    suspend fun assignExerciseToDay(exerciseId: Long, weekdayId: Long) {
        val join = ExerciseWeekDayJoin(exerciseId, weekdayId)
        exerciseWeekDayJoinDao.insert(join)
    }

    fun getExercisesForWeekDay(weekdayId: Long): LiveData<List<Exercise>> {
        return exerciseWeekDayJoinDao.getExercisesForWeekDay(weekdayId)
    }

    fun getWeekDaysForExercise(exerciseId: Long): LiveData<List<WeekDay>> {
        return exerciseWeekDayJoinDao.getWeekDaysForExercise(exerciseId)
    }

    suspend fun getExerciseById(id: Long): Exercise? {
        return exerciseDao.getExerciseById(id)
    }

    suspend fun removeExerciseFromDay(exerciseId: Long, weekdayId: Long) {
        exerciseWeekDayJoinDao.removeExerciseFromDay(exerciseId, weekdayId)
    }


    fun getDropSetsForExercise(exerciseId: Long): LiveData<List<DropSet>> {
        return dropSetDao.getDropSetsForExercise(exerciseId)
    }
    suspend fun insertDropSets(exerciseId: Long, dropSets: List<DropSetEntry>) {
        dropSets.forEach { dropSet ->
            dropSetDao.insertDropSet(
                DropSet(
                    exerciseId = exerciseId,
                    weight = dropSet.weight,
                    repetitions = dropSet.repetitions
                )
            )
        }
    }

}