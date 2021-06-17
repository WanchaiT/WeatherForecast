package com.example.weatherforecast.featureWeatherForecast.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherForecastModel(
    @SerializedName("coord")
    var coord: CoordModel? = null,
    @SerializedName("weather")
    var weather: ArrayList<WeatherModel>? = null,
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("main")
    var main: MainModel? = null,
    @SerializedName("visibility")
    var visibility: Int? = null,
    @SerializedName("clouds")
    var clouds: CloudsModel? = null,
    @SerializedName("dt")
    var dt: Long? = null,
    @SerializedName("sys")
    var sys: SysModel? = null,
    @SerializedName("wind")
    var wind: WindModel? = null,
    @SerializedName("timezone")
    var timezone: Int? = 0,
    @SerializedName("id")
    var id: Long? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("cod")
    var cod: Int? = null
) : Parcelable {
}

@Parcelize
class CoordModel(
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("lat")
    var lat: Double? = null
) : Parcelable

@Parcelize
class WeatherModel(
    @SerializedName("id")
    var id: Double? = null,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null
) : Parcelable

@Parcelize
class MainModel(
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("temp_min")
    var tempMin: Double? = null,
    @SerializedName("temp_max")
    var tempMax: Double? = null,
    @SerializedName("pressure")
    var pressure: Double? = null,
    @SerializedName("humidity")
    var humidity: Double? = null
) : Parcelable

@Parcelize
class CloudsModel(
    @SerializedName("all")
    var all: Int? = null
) : Parcelable

@Parcelize
class SysModel(
    @SerializedName("type")
    var type: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("sunrise")
    var sunrise: Long? = null,
    @SerializedName("sunset")
    var sunset: Long? = null
) : Parcelable

@Parcelize
class WindModel(
    @SerializedName("speed")
    var speed: Double? = null,
    @SerializedName("deg")
    var deg: Double? = null
) : Parcelable

