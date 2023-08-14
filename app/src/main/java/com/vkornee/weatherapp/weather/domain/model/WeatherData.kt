package com.vkornee.weatherapp.weather.domain.model

data class WeatherData(
    val location: Location,
    val temp: Double,
    val feelsLike: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val wind: Wind,
    val cloudsPercent: Int,
    val pressure: Int,
)

data class Location(
    val id: Int,
    val city: String,
    val country: String?
)

data class Wind(
    val gust: Double,
    val directionDegrees: Int,
    val speed: Double
)

enum class TempUnit { Celsius, Fahrenheit }
