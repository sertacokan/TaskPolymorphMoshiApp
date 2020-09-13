package com.example.taskapp.network

import com.squareup.moshi.Json

sealed class DataResponse(@Json(name = "Type") val types: DataTypes)

data class TaskImageDataResponse(
    val Src: String?,
    val Width: String?,
    val Height: String?,
) : DataResponse(DataTypes.Image)

data class TaskTextDataResponse(
    val Title: String?,
    val Spot: String?
) : DataResponse(DataTypes.Text)

data class TaskULDataResponse(
    val Content: String?
) : DataResponse(DataTypes.Ul)

data class TaskEmbedDataResponse(
    val Src: String?
) : DataResponse(DataTypes.Embed)

data class TaskBtnDataResponse(
    val Info: String?
) : DataResponse(DataTypes.Btn)

enum class DataTypes {
    Text,
    Image,
    Btn,
    Embed,
    Ul
}