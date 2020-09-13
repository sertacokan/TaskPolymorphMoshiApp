package com.example.taskapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestBuilder {

    companion object {

        private var taskService: TaskService? = null

        fun getTaskService(jsonString: ByteArray?): TaskService? {
            if (taskService == null) {
                val moshi = Moshi.Builder()
                    .add(
                        PolymorphicJsonAdapterFactory.of(DataResponse::class.java, "Type")
                            .withSubtype(TaskImageDataResponse::class.java, DataTypes.Image.name)
                            .withSubtype(TaskTextDataResponse::class.java, DataTypes.Text.name)
                            .withSubtype(TaskEmbedDataResponse::class.java, DataTypes.Embed.name)
                            .withSubtype(TaskULDataResponse::class.java, DataTypes.Ul.name)
                            .withSubtype(TaskBtnDataResponse::class.java, DataTypes.Btn.name)
                    )
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val interceptor = TaskInterceptor(jsonString ?: ByteArray(1))

                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build()

                taskService = retrofit.create(TaskService::class.java)
            }

            return taskService
        }
    }

}