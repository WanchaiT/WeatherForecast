package com.example.weatherforecast.featureWeatherForecast.fragment

import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.weatherforecast.R
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastResponse
import com.example.weatherforecast.featureWeatherForecast.model.WeatherForecastModel
import com.example.weatherforecast.util.ColorUtil
import com.example.weatherforecast.util.IOUtil.setHideSoftKeyBoard
import com.example.weatherforecast.util.NumberUtil.CelsiusToFahrenheit
import com.example.weatherforecast.util.NumberUtil.kelvinToCelsius
import com.example.weatherforecast.util.NumberUtil.twoDecimalToString
import com.example.weatherforecast.weather.viewModel.MainWeatherForecastViewModel
import kotlinx.android.synthetic.main.item_forecast_main.view.*
import kotlinx.android.synthetic.main.main_weather_forecast_fragment.*

class MainWeatherForecastFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainWeatherForecastFragment()
    }

    private lateinit var viewModel: MainWeatherForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_weather_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainWeatherForecastViewModel::class.java)
        viewModel.context = requireContext()
        observeViewModel()
        intiViewEvent()
    }

    private fun observeViewModel() {
        viewModel.let { event ->
            event.weatherForecast.observe(viewLifecycleOwner, Observer {
                setViewForecast(it)
            })
            event.onFailed.observe(viewLifecycleOwner, Observer {
                showDialogError(it)
            })
            event.onSuccessResponseHourForecast.observe(viewLifecycleOwner, Observer {
                navigateToHourForecastFragment(view!!, viewModel.getCityName(), viewModel.getTimeZone(), it ?: HourForecastResponse())
            })
        }
    }

    private fun intiViewEvent() {
        if (viewModel.isSearched) {
            lin_tutorial.setVisibility(false)
        }
        edt_search_city.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    setHideSoftKeyBoard(requireActivity(), this.view!!)
                    viewModel.isSearched = true
                    lin_tutorial.setVisibility(false)
                    viewModel.getWeatherForecast(textView.text.toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
        edt_search_city.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotBlank()) {
                    tv_clear.setVisibility(true)
                } else {
                    tv_clear.setVisibility(false)
                }
            }
        })
        tv_clear.setOnClickListener {
            edt_search_city.editableText.clear()
        }
        switch_temp.setOnClickListener {
            var temperature = viewModel.weatherForecast.value?.main?.temp?.kelvinToCelsius()
            setDataTemperature(temperature)
        }
        lin_whole_day.setOnClickListener {
            viewModel.getHourForecast(viewModel.weatherForecast.value?.coord?.lat ?: 0.0, viewModel.weatherForecast.value?.coord?.lon ?: 0.0)
        }
    }

    fun showDialogError(data: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(data)
            .setPositiveButton("Ok", null)
            .show()
    }

    fun setViewForecast(model: WeatherForecastModel) {
        setViewTemperature(model)
        setViewHumidity(model)
        setTextWeatherToday(model?.name ?: "there")
        ctv_main_forecast.setCityName(model?.name ?: "there")
        lin_whole_day.setVisibility(true)
        lin_forecast.setVisibility(true)
    }

    fun setTextWeatherToday(cityName: String) {
        tv_weather_today.text = "Weather today at ${cityName}"
    }

    fun setViewTemperature(model: WeatherForecastModel) {
        var temperature = model.main?.temp?.kelvinToCelsius()
        ctv_main_forecast.setBackgroundItemColor(ColorUtil.getColorTemperature(temperature ?: 0.0, requireContext()))
        setDataTemperature(temperature)
    }

    fun setDataTemperature(temperature: Double?) {
        if (switch_temp.isChecked) {
            var temperatureFahrenheit = temperature?.CelsiusToFahrenheit()
            ctv_main_forecast.setDataTemp(twoDecimalToString(temperatureFahrenheit ?: 0.0))
            ctv_main_forecast.setUnitTemp("ºF")
        } else {
            ctv_main_forecast.setDataTemp(twoDecimalToString(temperature ?: 0.0))
            ctv_main_forecast.setUnitTemp("ºC")
        }
    }

    fun setViewHumidity(model: WeatherForecastModel) {
        var humidity = model.main?.humidity
        ctv_main_forecast.setDataHum(twoDecimalToString(humidity ?: 0.0))
        ctv_main_forecast.setUnitHum("%")
    }

    private fun View.setVisibility(isShow: Boolean) {
        this.visibility = if (isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun navigateToHourForecastFragment(view: View, cityName: String, timeZone: Int, listOfHourForecastModel: HourForecastResponse) {
        val direction = MainWeatherForecastFragmentDirections
                            .actionMainWeatherForecastFragmentToForecastFragment(
                                listOfHourForecastModel, cityName, timeZone
        )
        view.findNavController().navigate(direction)
    }
}