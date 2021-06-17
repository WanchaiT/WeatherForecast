package com.example.weatherforecast.weather.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.R
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastResponse
import com.example.weatherforecast.featureWeatherForecast.model.WeatherForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.WeatherModel
import com.example.weatherforecast.featureWeatherForecast.repository.OnCallbackListener
import com.example.weatherforecast.featureWeatherForecast.repository.WeatherRepository
import com.example.weatherforecast.util.SingleLiveEvent

class MainWeatherForecastViewModel : ViewModel() {
    var isSearched = false
    lateinit var context: Context
    var weatherRepository = WeatherRepository()
    private val _weatherForecastModel: MutableLiveData<WeatherForecastModel> = MutableLiveData()
    val weatherForecast: LiveData<WeatherForecastModel> = _weatherForecastModel
    var hourForecastModel: HourForecastResponse ?= null
    var onSuccessResponseHourForecast = SingleLiveEvent<HourForecastResponse>()
    var onFailed = SingleLiveEvent<String>()


    fun getWeatherForecast(cityName: String) {
        weatherRepository.getCurrentWeather(cityName, object : OnCallbackListener<WeatherForecastModel> {
            override fun onFailed() {
                onFailed.postValue(context.getString(R.string.text_something_went_wong))
            }

            override fun onFailed(res: String) {
                onFailed.postValue(res.toString())
            }

            override fun onFinished(response: WeatherForecastModel?) {
                if (response != null) {
                    _weatherForecastModel.value = response
                }
            }
        })
    }

    fun getHourForecast(lat: Double, lon: Double) {
        weatherRepository.getHourForecastWeather(lat, lon, object : OnCallbackListener<HourForecastResponse> {
            override fun onFailed() {
                onFailed.postValue(context.getString(R.string.text_something_went_wong))
            }

            override fun onFailed(res: String) {
                onFailed.postValue(res.toString())
            }

            override fun onFinished(response: HourForecastResponse?) {
                if (response != null) {
                    hourForecastModel = response
                    onSuccessResponseHourForecast.postValue(response)
                }
            }
        })
    }

    fun getCityName(): String {
        return weatherForecast.value?.name ?: ""
    }

    fun getTimeZone(): Int {
        return weatherForecast.value?.timezone ?: 0
    }
}