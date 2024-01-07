package com.example.assignemnt_fit.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignemnt_fit.datasource.PowerTrackRepository
import kotlinx.coroutines.launch

class WeekDayViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PowerTrackRepository(application)

    val weekDays: LiveData<List<WeekDay>> = repository.allWeekDays

    private val _exercises: MutableLiveData<List<Exercise>> = MutableLiveData()
    private val _exercisesForDay = MutableLiveData<List<Exercise>>()
    val exercisesForDay: LiveData<List<Exercise>> = _exercisesForDay

    val exercises: LiveData<List<Exercise>> get() = _exercises

    private val _day = MutableLiveData<WeekDay>()
    val day: LiveData<WeekDay> = _day

    fun loadDayById(weekdayId: Long) {
        viewModelScope.launch {
            repository.getDaysById(weekdayId).observeForever { weekDay ->
                _day.value = weekDay
            }
        }
    }
    fun updateTrainingTitle(weekdayId: Long, newTitle: String) {
        viewModelScope.launch {
            val dayToUpdate = repository.getDayById(weekdayId)
            dayToUpdate?.let {
                it.trainingTitle = newTitle
                repository.updateWeekDay(it)
            }
        }
    }

    fun insertDay(day: WeekDay) {
        viewModelScope.launch {
            repository.insertWeekDay(day)
        }
    }

    fun assignExerciseToDay(exerciseId: Long, weekdayId: Long) {
        viewModelScope.launch {
            repository.assignExerciseToDay(exerciseId, weekdayId)
        }
    }

    // Optionally, a method to handle multiple exercises at once
    fun assignExercisesToDay(weekdayId: Long, exerciseIds: List<Long>) {
        exerciseIds.forEach { exerciseId ->
            assignExerciseToDay(exerciseId, weekdayId)
        }
    }

    fun getExercisesForDay(weekdayId: Long): LiveData<List<Exercise>> {
        return repository.getExercisesForWeekDay(weekdayId)
    }
    fun loadExercisesForDay(weekdayId: Long) {
        viewModelScope.launch {
            val exercises = repository.getExercisesForWeekDay(weekdayId).value ?: emptyList()
            _exercisesForDay.postValue(exercises)
        }
    }
}