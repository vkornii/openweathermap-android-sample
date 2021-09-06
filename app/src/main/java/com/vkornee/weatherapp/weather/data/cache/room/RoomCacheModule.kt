package com.vkornee.weatherapp.weather.data.cache.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomCacheModule {

    companion object {
        @Provides
        fun provideRoomCacheDb(@ApplicationContext context: Context): WeatherDatabase =
            Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_cache")
                .build()

        @Provides
        fun provideWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.weatherDao()
    }
}