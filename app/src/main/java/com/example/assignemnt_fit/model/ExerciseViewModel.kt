package com.example.assignemnt_fit.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assignemnt_fit.datasource.PowerTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PowerTrackRepository = PowerTrackRepository(application)
    
    var exerciseList: LiveData<List<Exercise>> = repository.getAllExercises()
        private set

    fun insertExercise(newExercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(newExercise)
        }
    }
}