package com.example.weatherapp.domain.data.Xdayweather

import com.example.weatherapp.domain.data.Clouds
import com.example.weatherapp.domain.data.Weather

data class WeatherHourly(
    val dt: Int,
    val main: XDayMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: XDayWind,
    val visibility: Int,
    val pop: Double,
    val sys: XDaySys,
    val dt_txt: String
)
