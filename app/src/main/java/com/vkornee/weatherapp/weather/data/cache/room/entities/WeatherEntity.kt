package com.vkornee.weatherapp.weather.data.cache.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity.Companion.CITY_COLUMN
import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity.Companion.ID_COLUMN
import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = [CITY_COLUMN])],
    primaryKeys = [ID_COLUMN, CITY_COLUMN])
data class WeatherEntity(
    @ColumnInfo(name = ID_COLUMN)
    val id: Int,
    @ColumnInfo(name = CITY_COLUMN)
    val city: String,
    val temp: Double,
    val feelsLikeTemp: Double,
    val weather: String,
    val description: String
) {
    companion object {
        const val TABLE_NAME = "weather_entity"
        const val ID_COLUMN = "id"
        const val CITY_COLUMN = "city"
    }
}