package com.vkornee.weatherapp.weather.data.repository

import com.vkornee.weatherapp.weather.data.cache.IWeatherCache
import com.vkornee.weatherapp.weather.data.source.WeatherDataSource
import com.vkornee.weatherapp.weather.domain.model.*
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    @DataSource(SourceType.OPEN_WEATHER)
    private val dataSource: WeatherDataSource,
    @Cache(CacheType.ROOM)
    private val cache: IWeatherCache
): IWeatherRepository {

    override suspend fun getWeatherData(city: String): WeatherData =
        dataSource.getWeatherData(city).also { data ->
            cache.saveWeatherData(data)
        }

    override fun flowCityWeather(city: String): Flow<WeatherData> =
        cache.flowWeatherDetails(city)

}
