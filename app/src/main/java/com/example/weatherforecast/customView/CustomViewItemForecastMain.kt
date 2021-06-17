package com.example.weatherforecast.customView

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.item_forecast_main.view.*

class CustomViewItemForecastMain : ConstraintLayout {

    private var mContext: Context? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        initial()
    }

    private fun initial() {
        val inflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        val view = inflater?.inflate(R.layout.item_forecast_main, this)
    }

    fun setBackgroundItemColor(color: Int) {
        var colorHex = ContextCompat.getColor(mContext!!, color)
        var whiteHex = ContextCompat.getColor(mContext!!, R.color.white)

        val colors = intArrayOf(colorHex, whiteHex)

        val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TR_BL, colors)

        gradientDrawable.cornerRadius = 12.0F
        lin_detail_weather.setBackgroundDrawable(gradientDrawable)
    }

    fun setDataTemp(data: String) {
        tv_data_temp.text = data
    }

    fun setDataHum(data: String) {
        tv_data_hum.text = data
    }

    fun setUnitTemp(unit: String) {
        tv_unit_temp.text = unit
    }

    fun setUnitHum(unit: String) {
        tv_unit_hum.text = unit
    }

    fun setCityName(name: String) {
        tv_city_name.text = name
    }
}