package com.vkornee.weatherapp.weather.presentation.model

data class WeatherDetailsUiModel(
    val header: WeatherDetailsHeader,
    val tiles: List<Tile>,
    val hourlyForecast: List<Pair<String, String>>,
    val dayForecast: List<Pair<String, String>>
)

data class WeatherDetailsHeader(
    val location: String,
    val temp: String,
    val feelsLike: String,
    val description: String,
    val maxTemp: String,
    val minTemp: String,
    val dateTime: String
)

class Tile(
    val type: TileType,
    val value: String,
    val extras: String? = null,
)

enum class TileType {
    WindSpeed,
    RainChance,
    Pressure,
    UVIndex
}
