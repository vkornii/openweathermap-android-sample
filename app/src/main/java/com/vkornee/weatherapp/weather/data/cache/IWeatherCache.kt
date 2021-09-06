package com.vkornee.weatherapp.weather.data.cache

import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface IWeatherCache {
    suspend fun saveWeatherData(city: String, data: List<WeatherData>)

    fun flowWeatherData(city: String): Flow<List<WeatherData>>

    fun flowWeatherDetails(city: String, weatherId: Int): Flow<WeatherData>
}