package com.example.weatherforecast.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class CustomToolbar : ConstraintLayout {

    private var mContext: Context? = null

//    var backButtonClickListener: ToolbarClickListener? = null
    var backButtonClickListener: (View) -> Unit = { _ -> }


    constructor(context: Context,  attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        initial()
    }

    private fun initial() {
        val inflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        val view = inflater?.inflate(R.layout.custom_toolbar, this)
        lin_back?.setOnClickListener {
            backButtonClickListener(it)
        }
    }

    fun setTextTitle(text: String): CustomToolbar {
        tv_title?.visibility = View.VISIBLE
        tv_title?.text = text
        return this
    }

    fun visibleBackButton(isVisible: Boolean) {
        lin_back?.visibility = if(isVisible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }
}

interface ToolbarClickListener {
    fun onLHSClickListener(view: View){}
}