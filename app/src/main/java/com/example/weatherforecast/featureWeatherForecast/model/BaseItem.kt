package com.example.weatherforecast.featureWeatherForecast.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseItem(
    var itemType: Int
) : Parcelable