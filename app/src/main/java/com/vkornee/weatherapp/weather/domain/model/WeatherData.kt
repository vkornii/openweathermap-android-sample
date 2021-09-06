package com.vkornee.weatherapp.weather.domain.model

data class WeatherData (
    val id: Int,
    val temp: Double,
    val feelsLikeTemp: Double,
    val weather: String,
    val description: String
)