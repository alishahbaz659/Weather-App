package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.data.localDatabase.AppDao
import com.example.weatherapp.data.localDatabase.WeatherDatabase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun getWeatherDb(context: Application): WeatherDatabase {
        return WeatherDatabase.getWeatherDatabase(context)
    }

    @Provides
    @Singleton
    fun getDao(weatherDb: WeatherDatabase): AppDao {
        return weatherDb.getDAO()
    }


}