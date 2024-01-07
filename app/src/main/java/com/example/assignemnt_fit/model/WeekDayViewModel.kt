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
    val exercises: LiveData<List<Exercise>> get() = _exercises

    private val _day = MutableLiveData<WeekDay>()
    val day: LiveData<WeekDay> = _day

    fun loadDayById(dayId: Long) {
        viewModelScope.launch {
            repository.getDayById(dayId).observeForever { weekDay ->
                _day.value = weekDay
            }
        }
    }
    fun insertDay(day: WeekDay) {
        viewModelScope.launch {
            repository.insertWeekDay(day)
        }
    }


}