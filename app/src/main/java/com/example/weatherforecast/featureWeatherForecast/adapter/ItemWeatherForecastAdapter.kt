package com.example.weatherforecast.featureWeatherForecast.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.featureWeatherForecast.model.BaseItem
import com.example.weatherforecast.featureWeatherForecast.model.HourForecastModel
import com.example.weatherforecast.util.ColorUtil
import com.example.weatherforecast.util.DateUtil
import com.example.weatherforecast.util.NumberUtil
import com.example.weatherforecast.util.NumberUtil.CelsiusToFahrenheit
import com.example.weatherforecast.util.NumberUtil.kelvinToCelsius
import kotlinx.android.synthetic.main.item_weather_forecast.view.*
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

enum class ItemType(var type: Int) {
    HOUR_FORECAST(1)
}

class ItemWeatherForecastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList = ArrayList<BaseItem>()
    var context: Context? = null

    internal var onItemClickListener: (Any) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HOUR_FORECAST.type -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather_forecast, parent, false)
                ItemHourForecastViewHolder(view)
            } else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather_forecast, parent, false)
                ItemHourForecastViewHolder(view)
            }
        }

    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemHourForecastViewHolder
        val item = itemList[position]
        when (holder) {
            is ItemHourForecastViewHolder -> {
                item as HourForecastModel
                holder.bind(item, onItemClickListener, context)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].itemType
    }

    class ItemHourForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: HourForecastModel,
            onItemClickListener: (HourForecastModel) -> Unit,
            context: Context?
        ) {
            setEventListener(item, onItemClickListener)
            setView(item, context)
        }

        private fun setEventListener(item: HourForecastModel,
                                     onItemClickListener: (HourForecastModel) -> Unit) {
//            itemView.lin_container.setOnClickListener {
//                onItemClickListener(item)
//            }
            itemView.switch_temp.setOnClickListener {
                var temperature = item.temp?.kelvinToCelsius()
                setDataTemperature(temperature)
            }
        }

        private fun setView(item: HourForecastModel, context: Context?) {
            setDateTime(item)
            setViewTemperature(item.temp ?: 0.0, context)
            setViewHumidity(item.humidity ?: 0.0, context)
        }

        fun setDateTime(item: HourForecastModel) {
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
            var offsetTime = (DateUtil.getOffsetFromUtc().toString() + "000").toInt()
            val millisecString = item.dt + "000"
            var timeZone = item.timeZone.toString() + "000"
            timeZone = (timeZone.toInt() - offsetTime).toString()
            itemView.tv_date.text = DateUtil.getDate(millisecString.toLong() + timeZone.toLong(), format)
        }

        fun setViewTemperature(temp: Double, context: Context?) {
            var temperature = temp.kelvinToCelsius()
            itemView.ctv_temp.setBackgroundItemColor(ColorUtil.getColorTemperature(temperature ?: 0.0, context!!))
            itemView.ctv_temp.setTitle(context.getString(R.string.text_temperature), R.color.black)
            setDataTemperature(temperature)
        }

        fun setDataTemperature(temperature: Double?) {
            if (itemView.switch_temp.isChecked) {
                var temperatureFahrenheit = temperature?.CelsiusToFahrenheit()
                var tempString = NumberUtil.twoDecimalToString(temperatureFahrenheit ?: 0.0)
                setDataTemp(tempString, "ºF")
            } else {
                var tempString = NumberUtil.twoDecimalToString(temperature ?: 0.0)
                setDataTemp(tempString, "ºC")
            }
        }

        fun setDataTemp(temp: String, unit: String) {
            itemView.ctv_temp.setData(temp)
            itemView.ctv_temp.setUnit(unit)
        }

        fun setViewHumidity(humidity: Double, context: Context?) {
            var humidityString = NumberUtil.twoDecimalToString(humidity ?: 0.0)
            itemView.ctv_hum.setBackgroundItemColor(ColorUtil.getColorHumidity(humidity ?: 0.0 , context!!))
            itemView.ctv_hum.setTitle(context.getString(R.string.text_humidity),  R.color.black)
            itemView.ctv_hum.setData(humidityString)
            itemView.ctv_hum.setUnit("%")
        }
    }
}
