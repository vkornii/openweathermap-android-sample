package com.vkornee.weatherapp.weather.data.cache.datastore

import kotlinx.coroutines.flow.Flow

interface IPreferences {

    suspend fun saveCity(cityName: String)

    fun cityFlow(): Flow<String?>
}
