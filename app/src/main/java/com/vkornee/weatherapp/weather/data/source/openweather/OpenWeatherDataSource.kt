package com.vkornee.weatherapp.weather.data.source.openweather

import com.vkornee.weatherapp.weather.data.source.WeatherDataSource
import com.vkornee.weatherapp.weather.data.source.openweather.api.IOpenWeatherMapApi
import com.vkornee.weatherapp.weather.data.source.openweather.mapper.OpenWeatherDataMapper
import com.vkornee.weatherapp.weather.domain.model.UnknownCityException
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import retrofit2.HttpException
import java.lang.Exception
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Named

class OpenWeatherDataSource @Inject constructor(
    private val openWeatherApi: IOpenWeatherMapApi,
    @Named("OpenWeatherMapApiKey")
    private val apiKey: String,
    @Named("TempUnits")
    private val units: String,
    private val openWeatherDataMapper: OpenWeatherDataMapper
): WeatherDataSource {

    override suspend fun getWeatherData(city: String): List<WeatherData> =
        try {
            openWeatherApi.getWeather(city, apiKey, units)
                .let(openWeatherDataMapper::map)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    if (e.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                        throw UnknownCityException(city)
                    }
                }
            }
            throw e
        }

}