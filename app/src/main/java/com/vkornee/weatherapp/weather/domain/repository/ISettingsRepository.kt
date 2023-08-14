package com.vkornee.weatherapp.weather.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingsRepository {
    fun selectedCityFlow(): Flow<String?>
    suspend fun saveCity(cityName: String)
}
