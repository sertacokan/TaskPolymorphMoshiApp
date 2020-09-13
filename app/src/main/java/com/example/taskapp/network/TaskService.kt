package com.example.taskapp.network

import retrofit2.http.GET

interface TaskService {

    @GET("todos")
    suspend fun getTaskList(): List<List<DataResponse>>
}