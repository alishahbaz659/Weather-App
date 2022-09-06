package com.example.weatherapp.di

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.Weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {
    @Binds
    @Singleton
    abstract fun bindWeatherModule(weatherMode: WeatherRepositoryImpl): WeatherRepository

}