package com.example.assignemnt_fit.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.assignemnt_fit.datasource.PowerTrackRepository


class WeekDayViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PowerTrackRepository = PowerTrackRepository(application)

    var weekDayList: LiveData<List<WeekDay>> = repository.getAllWeekDays()
        private set

//    fun insertWeekDay(newWeekday: WeekDay) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insert(newWeekday)
//        }
//    }

    // If needed, add more methods here to handle updates, deletes, or specific queries
    // related to WeekDays.

    // Example method to get WeekDay by ID
    fun getWeekDayById(dayId: Int): LiveData<WeekDay> {
        return repository.getWeekDayById(dayId)
    }
}

