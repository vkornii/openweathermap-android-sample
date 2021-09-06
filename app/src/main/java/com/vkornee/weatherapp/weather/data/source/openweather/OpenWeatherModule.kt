package com.vkornee.weatherapp.weather.data.source.openweather

import com.vkornee.weatherapp.weather.data.source.openweather.api.IOpenWeatherMapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenWeatherModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): IOpenWeatherMapApi =
        retrofit.create(IOpenWeatherMapApi::class.java)
}

