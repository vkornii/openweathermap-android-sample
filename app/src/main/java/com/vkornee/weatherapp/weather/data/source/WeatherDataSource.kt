package com.vkornee.weatherapp.weather.data.source

import com.vkornee.weatherapp.weather.domain.model.WeatherData
import javax.inject.Qualifier

interface WeatherDataSource {
    suspend fun getWeatherData(city: String): WeatherData

    suspend fun getForecastData(city: String): WeatherData
}

