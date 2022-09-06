package com.example.weatherapp.domain.data.Xdayweather

data class XDayMain(
    val temp: Double,
    val feelsLike: Double,
    val tempMax: Double,
    val tempMin: Double,
    val pressure: Double,
    val seaLevel: Double,
    val grndLevel: Double,
    val humidity: Int,
    val tempKf: Double
)
