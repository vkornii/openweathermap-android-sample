package com.vkornee.weatherapp.weather.presentation.di

import com.vkornee.weatherapp.BuildConfig
import com.vkornee.weatherapp.weather.data.cache.IWeatherCache
import com.vkornee.weatherapp.weather.data.cache.datastore.DataStorePreferences
import com.vkornee.weatherapp.weather.data.cache.datastore.IPreferences
import com.vkornee.weatherapp.weather.data.cache.room.WeatherCache
import com.vkornee.weatherapp.weather.data.repository.SettingsRepository
import com.vkornee.weatherapp.weather.data.repository.WeatherRepository
import com.vkornee.weatherapp.weather.data.source.WeatherDataSource
import com.vkornee.weatherapp.weather.data.source.openweather.OpenWeatherDataSource
import com.vkornee.weatherapp.weather.domain.model.Cache
import com.vkornee.weatherapp.weather.domain.model.CacheType
import com.vkornee.weatherapp.weather.domain.model.DataSource
import com.vkornee.weatherapp.weather.domain.model.SourceType
import com.vkornee.weatherapp.weather.domain.repository.ISettingsRepository
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherModule {

    @Binds
    @Singleton
    fun provideRepository(repository: WeatherRepository): IWeatherRepository

    @Binds
    @Singleton
    fun provideSettingsRepository(repository: SettingsRepository): ISettingsRepository

    @Binds
    @Singleton
    fun providePreferences(preferences: DataStorePreferences): IPreferences

    @Binds
    @Singleton
    @Cache(CacheType.ROOM)
    fun provideCache(cache: WeatherCache): IWeatherCache

    @Binds
    @Singleton
    @DataSource(SourceType.OPEN_WEATHER)
    fun provideDataSource(source: OpenWeatherDataSource): WeatherDataSource

    companion object {
        @Provides
        @Named("OpenWeatherMapApiKey")
        fun provideOpenWeatherApiKey() = BuildConfig.OPEN_WEATHER_MAP_API_KEY

        @Provides
        @Named("TempUnits")
        fun provideUnits() = BuildConfig.OPEN_WEATHER_MAP_UNIT
    }

}
