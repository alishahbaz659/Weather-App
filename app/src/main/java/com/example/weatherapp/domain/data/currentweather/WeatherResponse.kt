package com.example.weatherapp.domain.data.currentweather

import com.example.weatherapp.domain.data.Clouds
import com.example.weatherapp.domain.data.Coord
import com.example.weatherapp.domain.data.Weather

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)