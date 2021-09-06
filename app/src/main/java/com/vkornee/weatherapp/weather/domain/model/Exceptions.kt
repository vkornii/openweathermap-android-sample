package com.vkornee.weatherapp.weather.domain.model

class UnknownCityException(val city: String): Exception("Unknown city $city")