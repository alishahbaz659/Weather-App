package com.example.weatherapp.domain.Weather

import com.example.weatherapp.domain.data.Xdayweather.XDayWeatherResponse
import com.example.weatherapp.domain.data.currentweather.WeatherResponse

interface WeatherRepository {
    fun getCurrentWeather(cityName: String): WeatherResponse?
    fun getCurrentLocationWeather(lat: Double, lon: Double): WeatherResponse?
    fun getXDayWeather(cityName: String): XDayWeatherResponse?
}