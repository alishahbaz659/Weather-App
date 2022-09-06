package com.example.weatherapp.domain.adapter

import android.view.View

interface AdapterInterface {
    fun OnClickListener(position: Int,cityName:String ,view: View)
}