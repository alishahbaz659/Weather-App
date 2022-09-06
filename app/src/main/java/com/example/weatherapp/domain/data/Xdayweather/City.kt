package com.example.weatherapp.domain.data.Xdayweather

import com.example.weatherapp.domain.data.Coord

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val county: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)
