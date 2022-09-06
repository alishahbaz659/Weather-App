package com.example.weatherapp.data.localDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getDAO(): AppDao

    companion object {
        private var dbInstance: WeatherDatabase? = null

        fun getWeatherDatabase(context: Context): WeatherDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder<WeatherDatabase>(
                    context.applicationContext, WeatherDatabase::class.java, "WEATHERDB"
                ).allowMainThreadQueries().build()
            }
            return dbInstance!!
        }
    }
}