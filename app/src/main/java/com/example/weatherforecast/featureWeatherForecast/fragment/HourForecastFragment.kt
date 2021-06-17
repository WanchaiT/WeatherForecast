package com.example.weatherforecast.featureWeatherForecast.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.featureWeatherForecast.adapter.ItemWeatherForecastAdapter
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastResponse
import com.example.weatherforecast.featureWeatherForecast.viewModel.ForecastViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.hour_forecast_fragment.*

class HourForecastFragment : Fragment() {

    var cityName: String? = null
    var timeZone: Int? = null
    var listOfHourForecastModel: HourForecastResponse? = null
    var rcvHourForecastAdapter = ItemWeatherForecastAdapter()

    companion object {
        fun newInstance() = HourForecastFragment()
    }

    private lateinit var viewModel: ForecastViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hour_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        initArgumentFromMainWeather()
        intiRecycleView()
        setCityName()
    }

    private fun setCityName() {
        tv_city_name.text = cityName
    }

    private fun initArgumentFromMainWeather() {
        arguments?.let {
            val args = HourForecastFragmentArgs.fromBundle(it)
            listOfHourForecastModel = args.listOfHourForecast
            cityName = args.cityName
            timeZone = args.timeZone
        }
    }

    private fun intiRecycleView() {
//        rcvHourForecastAdapter.onItemClickListener = { item ->
//            item.id?.let {
//            }
//        }
        rcv_hour_forecast.layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
        rcv_hour_forecast.adapter = rcvHourForecastAdapter
        listOfHourForecastModel?.hourForecastModel?.forEach { item ->
            item.apply {
                timeZone = this@HourForecastFragment.timeZone
            }
            rcvHourForecastAdapter.itemList.add(item)
        }
        rcvHourForecastAdapter.context = context
        rcvHourForecastAdapter.notifyDataSetChanged()
    }

}