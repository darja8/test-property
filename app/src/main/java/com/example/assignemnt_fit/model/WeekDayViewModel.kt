package com.example.assignemnt_fit.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignemnt_fit.datasource.PowerTrackRepository

class WeekDayViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PowerTrackRepository(application)
    val weekDays: LiveData<List<WeekDay>> = repository.allWeekDays

    private val _exercises: MutableLiveData<List<Exercise>> = MutableLiveData()
    val exercises: LiveData<List<Exercise>> get() = _exercises
    suspend fun assignExercisesToDay(dayId: Long, exerciseIds: List<Long>) {
        val joinRecords = exerciseIds.map { exerciseId ->
            ExerciseWeekDayJoin(exerciseId, dayId)
        }
        ExerciseWeekDayJoinDao.insertAll(joinRecords.toTypedArray())
    }
    fun getExercisesForDay(dayId: Long): LiveData<List<Exercise>> {
        return repository.getExercisesForDay(dayId)
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            dao.insertExercise(exercise)
        }
    }

    fun addWeekDay(weekDay: WeekDay) {
        viewModelScope.launch {
            dao.insertWeekDay(weekDay)
        }
    }

    fun linkExerciseToDay(exerciseId: Long, dayId: Long) {
        viewModelScope.launch {
            dao.insertExerciseWeekDayJoin(ExerciseWeekDayJoin(exerciseId, dayId))
        }
    }



}