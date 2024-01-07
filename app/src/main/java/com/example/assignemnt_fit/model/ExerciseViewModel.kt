package com.example.assignemnt_fit.model
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignemnt_fit.datasource.PowerTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PowerTrackRepository = PowerTrackRepository(application)

    val allExercises: LiveData<List<Exercise>> = repository.fetchAllExercises()

    fun insertExercise(newExercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(newExercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExercise(exercise)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateExercise(exercise)
        }
    }


    fun getExerciseById(id: Long): LiveData<Exercise?> {
        val result = MutableLiveData<Exercise?>()
        viewModelScope.launch {
            result.value = repository.getExerciseById(id)
        }
        return result
    }

}