package com.example.weatherforecast.featureWeatherForecast.repository

import android.widget.Toast
import com.example.weatherforecast.featureWeatherForecast.api.ApiClient
import com.example.weatherforecast.featureWeatherForecast.api.ApiWeather
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastResponse
import com.example.weatherforecast.featureWeatherForecast.model.WeatherForecastModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherRepository() {
    var apiClient = ApiClient()

    fun getCurrentWeather(cityName: String, onCallbackListener: OnCallbackListener<WeatherForecastModel>) {
        var apiInterface = apiClient.build().create(ApiWeather::class.java)
        var call = apiInterface.getWeather(cityName)

        call.enqueue(object : Callback<WeatherForecastModel> {
            override fun onResponse(call: Call<WeatherForecastModel>, response: Response<WeatherForecastModel>) {
                if (response.isSuccessful) {
                    onCallbackListener.onFinished(response.body())
                } else {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    var message = jObjError.getString("message")
                    onCallbackListener.onFailed(message)
                }
            }

            override fun onFailure(call: Call<WeatherForecastModel>, t: Throwable) {
                onCallbackListener.onFailed()
            }
        })
    }

    fun getHourForecastWeather(lat: Double, lon: Double, onCallbackListener: OnCallbackListener<HourForecastResponse>) {
        var apiInterface = apiClient.build().create(ApiWeather::class.java)
        var call = apiInterface.getHourForecast(lat, lon)

        call.enqueue(object : Callback<HourForecastResponse> {
            override fun onResponse(call: Call<HourForecastResponse>, response: Response<HourForecastResponse>) {
                if (response.isSuccessful) {
                    onCallbackListener.onFinished(response.body())
                } else {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    var message = jObjError.getString("message")
                    onCallbackListener.onFailed(message)
                }
            }

            override fun onFailure(call: Call<HourForecastResponse>, t: Throwable) {
                onCallbackListener.onFailed()
            }
        })
    }
}

interface OnCallbackListener<in T> {
    fun onFinished(response: T?)
    fun onFailed()
    fun onFailed(res : String)
}