package com.example.weatherforecast.featureWeatherForecast.model

import android.os.Parcelable
import com.example.weatherforecast.featureWeatherForecast.adapter.ItemType
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class HourForecastModel(
        @SerializedName("dt")
        var dt: String? = null,
        @SerializedName("temp")
        var temp: Double? = null,
        @SerializedName("feels_like")
        var feelsLike: Double? = null,
        @SerializedName("pressure")
        var pressure: Double? = null,
        @SerializedName("humidity")
        var humidity: Double? = null,
        @SerializedName("dew_point")
        var dewPoint: Double? = null,
        @SerializedName("uvi")
        var uvi: Double? = null,
        @SerializedName("clouds")
        var clouds: Int? = null,
        @SerializedName("visibility")
        var visibility: Double? = null,
        @SerializedName("wind_speed")
        var windSpeed: Double? = null,
        @SerializedName("wind_deg")
        var windDeg: Int? = null,
        @SerializedName("weather")
        var weather: ArrayList<WeatherModel>? = null,
        @SerializedName("pop")
        var pop: Double? = null,

        var timeZone: Int? = 0


) : Parcelable, BaseItem(ItemType.HOUR_FORECAST.type) {
}

@Parcelize
class HourForecastResponse(
        @SerializedName("hourly")
        var hourForecastModel: ArrayList<HourForecastModel>? = null
) : Parcelable, BaseItem(ItemType.HOUR_FORECAST.type)