package com.example.taskapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        viewModel.jsonString.value = jsonString()

        viewModel.taskList.observe(this) {
            Log.d("TaskList", it.toString())
            response_text.text = it.toString().split("),", "]").joinToString(separator = "\n\n")
        }
    }

    private fun jsonString(): ByteArray? {
        return try {
            val stream = assets.open("task.json")
            val size = stream.available()
            val byteArray = ByteArray(size)

            stream.read(byteArray)
            stream.close()

            byteArray
        } catch (e: IOException) {
            null
        }
    }
}