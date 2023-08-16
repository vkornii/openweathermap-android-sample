package com.vkornee.weatherapp.weather.presentation.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Instant

data class WeatherDetailsUiModel(
    val header: WeatherDetailsHeader,
    val tiles: List<Tile>,
    val hourlyForecast: List<Pair<String, String>>,
    val dayForecast: List<Pair<String, String>>,
    val selectedPeriod: WeatherPeriod = WeatherPeriod.Today
)

enum class WeatherPeriod { Today, Tomorrow, Days10 }

data class WeatherDetailsHeader(
    val location: String,
    val temp: String,
    val feelsLike: String,
    val description: String,
    val maxTemp: String,
    val minTemp: String,
    val dateTime: String
)

data class Tile(
    val type: TileType,
    val value: String,
    val extras: String? = null,
)

data class WeekForecastGraphData(
    val tempStep: Int = 10,
    val entries: ImmutableList<WeekForecastGraphEntry>
)

data class WeekForecastGraphEntry(
    val date: Instant,
    val temp: Int,
    val isNow: Boolean
)

enum class TileType {
    WindSpeed,
    RainChance,
    Pressure,
    UVIndex
}
