package com.vkornee.weatherapp.weather.data.repository

import com.vkornee.weatherapp.weather.data.cache.datastore.IPreferences
import com.vkornee.weatherapp.weather.domain.repository.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val preferences: IPreferences
): ISettingsRepository {

    override fun selectedCityFlow(): Flow<String?> = preferences.cityFlow()
    override suspend fun saveCity(cityName: String) = preferences.saveCity(cityName)
}
