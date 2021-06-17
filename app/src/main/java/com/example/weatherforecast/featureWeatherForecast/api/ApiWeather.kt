package com.example.weatherforecast.featureWeatherForecast.api

import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastResponse
import com.example.weatherforecast.featureWeatherForecast.model.WeatherForecastModel
import com.example.weatherforecast.util.ConstantUtil
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeather {
    @GET("weather")
    fun getWeather(
        @Query("q") name: String,
        @Query("appid") key: String = ConstantUtil.appKey
    ): Call<WeatherForecastModel>

    @GET("onecall")
    fun getHourForecast(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("exclude") exclude: String = "minutely",
            @Query("appid") key: String = ConstantUtil.appKey
    ): Call<HourForecastResponse>
}