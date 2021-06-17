package com.example.weatherforecast.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getDate(milliSeconds: Long, dateFormat: SimpleDateFormat): String? {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return dateFormat.format(calendar.time)
    }

    fun getCurrentTimeZone(): TimeZone {
        return TimeZone.getDefault()
    }

    fun getOffsetFromUtc() : Int {
        val timeZone = TimeZone.getDefault()
        val now = Date()
        return timeZone.getOffset(now.time) / 1000
    }
}