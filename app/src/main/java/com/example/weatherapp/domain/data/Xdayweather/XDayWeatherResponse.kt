package com.example.weatherapp.domain.data.Xdayweather

data class XDayWeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherHourly>,
    val city: City
)
