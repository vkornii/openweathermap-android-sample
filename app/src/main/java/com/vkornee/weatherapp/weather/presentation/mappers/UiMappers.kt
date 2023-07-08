package com.vkornee.weatherapp.weather.presentation.mappers

import com.vkornee.weatherapp.common.BaseMapper
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.presentation.model.Tile
import com.vkornee.weatherapp.weather.presentation.model.TileType
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsHeader
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import javax.inject.Inject

class WeatherDetailsUiMapper @Inject constructor()
    : BaseMapper<WeatherData, WeatherDetailsUiModel>() {
    override fun map(from: WeatherData) =
        WeatherDetailsUiModel(
            header = WeatherDetailsHeader(
                location = from.location.city,
                temp = "${from.temp}°",
                feelsLike = "${from.feelsLike}°",
                description = from.description,
                minTemp = "${from.minTemp}°",
                maxTemp = "${from.maxTemp}°",
                dateTime = "July 1, 18:20",
            ),
            tiles = listOf(
                Tile(
                    type = TileType.WindSpeed,
                    value = from.wind.speed.toString(), //todo format
                ),
                Tile(
                    TileType.Pressure,
                    value = from.pressure.toString() //todo format
                ),
            ),
            hourlyForecast = listOf(
                "Now" to "30°",
                "10am" to "32°",
                "11am" to "31°",
                "12am" to "25°",
                "13am" to "24°",
            ),
            dayForecast = emptyList()
        )
}
