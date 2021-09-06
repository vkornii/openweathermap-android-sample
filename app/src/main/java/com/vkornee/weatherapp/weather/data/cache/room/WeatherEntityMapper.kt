package com.vkornee.weatherapp.weather.data.cache.room

import com.vkornee.weatherapp.weather.data.cache.room.entities.WeatherEntity
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import javax.inject.Inject

class WeatherEntityMapper @Inject constructor() {

    fun map(city: String, weatherDataList: List<WeatherData>) =
        weatherDataList.map {
            WeatherEntity(
                id = it.id,
                city = city,
                temp = it.temp,
                feelsLikeTemp = it.feelsLikeTemp,
                weather = it.weather,
                description = it.description
            )
        }

    fun map(weatherEntities: List<WeatherEntity>) =
        weatherEntities.map { map(it) }

    fun map(weatherEntity: WeatherEntity) =
        WeatherData(
            weatherEntity.id,
            weatherEntity.temp,
            weatherEntity.feelsLikeTemp,
            weatherEntity.weather,
            weatherEntity.description
        )
}