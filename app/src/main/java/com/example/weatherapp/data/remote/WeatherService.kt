package com.example.weatherapp.data.remote

import com.example.weatherapp.domain.data.currentweather.WeatherResponse
import com.example.weatherapp.domain.data.Xdayweather.XDayWeatherResponse
import retrofit2.http.GET
import retrofit2.Call;
import retrofit2.http.Query

interface WeatherService {
    //get current weather data according to the city name
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String): Call<WeatherResponse>

    @GET("weather")
    fun getCurrentLocationWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String): Call<WeatherResponse>

    //get forecasting of five days
    @GET("forecast")
    fun getXDayWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String): Call<XDayWeatherResponse>


}

