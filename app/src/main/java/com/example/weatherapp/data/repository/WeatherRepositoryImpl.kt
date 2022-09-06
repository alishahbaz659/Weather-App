package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.data.currentweather.WeatherResponse
import com.example.weatherapp.domain.data.Xdayweather.XDayWeatherResponse
import com.example.weatherapp.data.remote.WeatherService
import com.example.weatherapp.domain.Weather.WeatherRepository
import com.example.weatherapp.domain.util.Constants.Companion.APPID
import com.example.weatherapp.domain.util.Constants.Companion.LANGUAGE
import com.example.weatherapp.domain.util.Constants.Companion.UNITS
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class WeatherRepositoryImpl @Inject constructor(
    private val webclient: WeatherService
) : WeatherRepository {
    override fun getCurrentWeather(cityName: String): WeatherResponse? {
        val currentWeather: WeatherResponse?
        val response =
            webclient.getCurrentWeather(cityName, UNITS, LANGUAGE, APPID).execute()
        currentWeather = response.body()
        return currentWeather
    }

    override fun getCurrentLocationWeather(lat: Double, lon: Double): WeatherResponse? {
        val currentWeather: WeatherResponse?
        val response =
            webclient.getCurrentLocationWeather(lat, lon, UNITS, LANGUAGE, APPID).execute()
        currentWeather = response.body()
        return currentWeather
    }

    override fun getXDayWeather(cityName: String): XDayWeatherResponse? {
        val currentWeather: XDayWeatherResponse?

        val response =
            webclient.getXDayWeather(cityName, UNITS, LANGUAGE, APPID).execute()
        currentWeather = response.body()
        return currentWeather
    }
}