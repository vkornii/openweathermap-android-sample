package com.vkornee.weatherapp.weather.domain.model


sealed class LocationSelection {
    object NotSelected : LocationSelection()
    data class City(val name: String) : LocationSelection()
}
