package com.example.viewmodeldemo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class TempViewModel : ViewModel() {
    // Composable func 에서는 아래와 같이
    // - var sliderPosition by remember { mutableStateOf(20f) }
    // - val checked = remember { mutableStateOf(defaultChecked) }
    var isFahrenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun convertTemp(temp: String) {
        result = try {
            val tempInt = temp.toInt()
            if (isFahrenheit) {
                ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                ((tempInt * 1.8) +32).roundToInt().toString()
            }
        } catch (e: java.lang.Exception) {
            "Invalid Entry"
        }
        Log.d("ViewModel", "covertTemp() temp:$temp to result:$result")
    }

    fun switchChange() {
        Log.d("ViewModel", "switchChange() isFahrenheit:$isFahrenheit to ${!isFahrenheit}")
        isFahrenheit = !isFahrenheit
    }
}