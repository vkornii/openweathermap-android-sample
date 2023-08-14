package com.vkornee.weatherapp.weather.data.cache.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vkornee.weatherapp.weather.data.cache.datastore.DataStorePreferences.PreferencesKeys.CITY_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "weather_app_user_preferences"

class DataStorePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) : IPreferences {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )


    override suspend fun saveCity(cityName: String) {
        context.dataStore.edit { prefs ->
            prefs[CITY_NAME] = cityName
        }
    }

    override fun cityFlow() = context.dataStore.data.map { prefs -> prefs[CITY_NAME] }

    private object PreferencesKeys {
        val CITY_NAME = stringPreferencesKey("city_name")
    }

}
