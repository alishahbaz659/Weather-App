package com.example.weatherapp.data.localDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM cities ORDER BY id DESC")
    fun getRecords(): List<WeatherEntity>

    @Insert
    fun insertRecord(weatherEntity: WeatherEntity)
}
