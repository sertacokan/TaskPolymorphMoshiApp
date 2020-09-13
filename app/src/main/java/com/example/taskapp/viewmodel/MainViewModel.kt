package com.example.taskapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.taskapp.repository.TaskRepository

class MainViewModel : ViewModel() {

    private val taskRepository: TaskRepository by lazy(LazyThreadSafetyMode.NONE) {
        TaskRepository()
    }

    val jsonString = MutableLiveData<ByteArray?>()

    val taskList = Transformations.switchMap(jsonString) {
        liveData {
            val value = taskRepository.getTaskList(it)
            Log.d("TaskRequestResult", value.toString())
            emit(value)
        }
    }
}