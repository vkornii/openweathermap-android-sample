package com.vkornee.weatherapp.weather.data.cache

import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface IWeatherCache {
    suspend fun saveWeatherData(data: WeatherData)

    fun flowWeatherDetails(city: String): Flow<WeatherData>

}
