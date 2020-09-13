package com.example.taskapp.repository

import android.util.Log
import com.example.taskapp.network.DataResponse
import com.example.taskapp.network.RestBuilder

class TaskRepository {

    suspend fun getTaskList(s: ByteArray?): List<List<DataResponse>>? {
        val taskList = RestBuilder.getTaskService(s)?.getTaskList()
        Log.d("TaskRequestResult", taskList.toString())
        return taskList
    }
}