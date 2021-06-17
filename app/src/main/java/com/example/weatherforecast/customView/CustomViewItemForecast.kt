package com.example.weatherforecast.customView

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.item_forecast.view.*


class CustomViewItemForecast : LinearLayout {

    private var mContext: Context? = null

    constructor(context: Context,  attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        initial()
    }

    private fun initial() {
        val inflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        val view = inflater?.inflate(R.layout.item_forecast, this)
    }

    fun setBackgroundItemColor(color: Int) {
        var colorHex = ContextCompat.getColor(mContext!!, color)
        var whiteHex = ContextCompat.getColor(mContext!!, R.color.white)

        val colors = intArrayOf(colorHex, whiteHex)

        val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TR_BL, colors)

        gradientDrawable.cornerRadius = 12.0F
        imv_background.setBackgroundDrawable(gradientDrawable)
    }

    fun setTitle(title: String, color: Int) {
        tv_title.text = title
        tv_title.setTextColor(color)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setData(data: String, color: Int) {
        tv_data.text = data
        tv_data.setTextColor(color)
    }

    fun setData(data: String) {
        tv_data.text = data
    }

    fun setUnit(unit: String, color: Int) {
        tv_unit.text = unit
        tv_unit.setTextColor(color)
    }

    fun setUnit(unit: String) {
        tv_unit.text = unit
    }

}
