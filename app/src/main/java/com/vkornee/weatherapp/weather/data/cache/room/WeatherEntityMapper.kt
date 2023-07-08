package com.vkornee.weatherapp.weather.data.cache.room

import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity
import com.vkornee.weatherapp.weather.domain.model.Location
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import javax.inject.Inject

class WeatherEntityMapper @Inject constructor() {

    fun map(weatherData: WeatherData) =
        WeatherEntity(
            id = weatherData.location.id,
            city = weatherData.location.city,
            temp = weatherData.temp,
            description = weatherData.description,
            feelsLike = weatherData.feelsLike,
            minTemp = weatherData.minTemp,
            maxTemp = weatherData.maxTemp,
            wind = weatherData.wind,
            cloudsPercent = weatherData.location.id,
            pressure = weatherData.location.id,
        )

    fun map(from: WeatherEntity) =
        WeatherData(
            location = Location(
                id = from.id,
                city = from.city,
                country = null,
            ),
            temp = from.temp,
            feelsLike = from.feelsLike,
            minTemp = from.minTemp,
            maxTemp = from.maxTemp,
            description = from.description,
            wind = from.wind,
            cloudsPercent = from.cloudsPercent,
            pressure = from.pressure,
        )
}
