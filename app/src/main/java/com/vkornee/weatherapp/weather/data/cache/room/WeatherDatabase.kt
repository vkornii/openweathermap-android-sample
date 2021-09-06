package com.vkornee.weatherapp.weather.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}