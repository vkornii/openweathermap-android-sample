package com.vkornee.weatherapp.weather.data.source.openweather.mapper

import com.vkornee.weatherapp.weather.data.source.openweather.model.OpenWeatherMapResponse
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import javax.inject.Inject

class OpenWeatherDataMapper @Inject constructor() {

    fun map(from: OpenWeatherMapResponse): List<WeatherData> =
        from.list.mapNotNull { weatherInfo ->
            val weather = weatherInfo.weather.firstOrNull()
            if (weather == null)
                null
            else
                WeatherData(
                    weather.id,
                    weatherInfo.main.temp,
                    weatherInfo.main.feels_like,
                    weather.main,
                    weather.description
                )
        }
}