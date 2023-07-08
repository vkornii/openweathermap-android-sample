package com.vkornee.weatherapp.weather.domain.repository

import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import javax.inject.Qualifier

interface IWeatherRepository {

    suspend fun getWeatherData(city: String): WeatherData

    fun flowCityWeather(city: String): Flow<WeatherData>

}
