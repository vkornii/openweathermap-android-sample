package com.vkornee.weatherapp.weather.data.cache.room

import com.vkornee.weatherapp.weather.data.cache.IWeatherCache
 import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherCache @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherEntityMapper: WeatherEntityMapper
): IWeatherCache {
    override suspend fun saveWeatherData(city: String, data: List<WeatherData>) {
        weatherDao.save(city, weatherEntityMapper.map(city, data))
    }

    override fun flowWeatherData(city: String): Flow<List<WeatherData>> =
        weatherDao.flowWeather(city)
            .map(weatherEntityMapper::map)

    override fun flowWeatherDetails(city: String, weatherId: Int): Flow<WeatherData> =
        weatherDao.flowWeatherDetails(city, weatherId)
            .map(weatherEntityMapper::map)
}