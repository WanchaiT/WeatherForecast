package com.example.weatherforecast.util

import java.text.DecimalFormat

object NumberUtil {
    fun twoDecimalToString(data: Double): String {
        val df = DecimalFormat("#.##")
        return df.format(data ?: 0.0)
    }

    fun Double.kelvinToCelsius(): Double {
        return this.minus(273.15)
    }

    fun Double.CelsiusToFahrenheit(): Double {
        return this.times(9.0/5.0) + 32
    }
}