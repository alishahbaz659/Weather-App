package com.example.weatherapp.data.repository

import com.example.weatherapp.data.localDatabase.AppDao
import com.example.weatherapp.data.localDatabase.WeatherEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val appDao: AppDao
) {
    fun getRecords(): List<WeatherEntity> {
        return appDao.getRecords()
    }

    fun insertRecord(weatherEntity: WeatherEntity) {
        appDao.insertRecord(weatherEntity)
    }
}