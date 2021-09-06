package com.vkornee.weatherapp.weather.data.source.openweather.api

import com.vkornee.weatherapp.weather.data.source.openweather.model.OpenWeatherMapResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeatherMapApi {

    @GET("forecast")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String): OpenWeatherMapResponse
}