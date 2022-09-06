package com.example.weatherapp.data.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
class WeatherEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id:Int=0,
    @ColumnInfo(name="cityName") val name:String
)