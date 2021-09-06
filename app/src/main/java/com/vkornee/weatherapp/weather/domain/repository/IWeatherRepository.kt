package com.vkornee.weatherapp.weather.domain.repository

import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import javax.inject.Qualifier

interface IWeatherRepository {

    suspend fun getWeatherData(city: String): List<WeatherData>

    fun flowCityWeather(city: String): Flow<List<WeatherData>>

    fun getWeatherDetails(city: String, weatherId: Int): Flow<WeatherData>
}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Repository