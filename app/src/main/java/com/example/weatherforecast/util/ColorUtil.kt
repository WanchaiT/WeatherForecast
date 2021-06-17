package com.example.weatherforecast.util

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.weatherforecast.R

object ColorUtil {

    fun getColorHumidity(data: Double, context: Context): Int {
        return when {
            data <= 10 -> R.color.green1
            data <= 20 -> R.color.green2
            data <= 30 -> R.color.green3
            data <= 40 -> R.color.green4
            data <= 50 -> R.color.green5
            data <= 60 -> R.color.green6
            data <= 70 -> R.color.green7
            data <= 80 -> R.color.green8
            data <= 90 -> R.color.green9
            data <= 100 -> R.color.green10
            else -> R.color.green1
        }
    }

    fun getColorTemperature(data: Double, context: Context): Int {
        return when {
            data <= 0 -> R.color.temp1
            data <= 10 -> R.color.temp2
            data <= 15 -> R.color.temp3
            data <= 20 -> R.color.temp4
            data <= 25 -> R.color.temp5
            data <= 30 -> R.color.temp6
            data <= 35 -> R.color.temp7
            data <= 40 -> R.color.temp8
            data <= 45 -> R.color.temp9
            data >= 46 -> R.color.temp10
            else -> R.color.temp1
        }
    }
}