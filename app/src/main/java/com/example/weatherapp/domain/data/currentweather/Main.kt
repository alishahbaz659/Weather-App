package com.example.weatherapp.domain.data.currentweather

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMax: Double,
    val tempMin: Double,
    val pressure: Double,
    val humidity: Int
)
