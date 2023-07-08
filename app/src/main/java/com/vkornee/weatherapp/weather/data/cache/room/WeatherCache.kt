package com.vkornee.weatherapp.weather.data.cache.room

import android.util.Log
import com.vkornee.weatherapp.weather.data.cache.IWeatherCache
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class WeatherCache @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherEntityMapper: WeatherEntityMapper
): IWeatherCache {
    override suspend fun saveWeatherData(data: WeatherData) {
        weatherDao.save(weatherEntityMapper.map(data))
    }

    override fun flowWeatherDetails(city: String): Flow<WeatherData> =
        weatherDao.flowWeather(city)
            .filterNotNull()
            .onEach {
                Log.w("kornienko", "en tity $it")
            }
            .map(weatherEntityMapper::map)

}
