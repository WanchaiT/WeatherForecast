package com.example.weatherforecast.featureWeatherForecast.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.weatherforecast.R
import com.example.weatherforecast.featureWeatherForecast.fragment.MainWeatherForecastFragment
import kotlinx.android.synthetic.main.activity_main_weather_forcast.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainWeatherForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_weather_forcast)
        this.window.statusBarColor = resources.getColor(R.color.black)
        initToolbar()
    }

    private fun initToolbar() {
        ctv_toolbar.visibleBackButton(false)
    }
}
