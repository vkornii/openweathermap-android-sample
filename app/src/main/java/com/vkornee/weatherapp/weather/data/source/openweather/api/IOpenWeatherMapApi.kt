package com.vkornee.weatherapp.weather.data.source.openweather.api

import com.vkornee.weatherapp.weather.data.source.openweather.model.OpenWeatherMapForecast
import com.vkornee.weatherapp.weather.data.source.openweather.model.OpenWeatherMapWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeatherMapApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String): OpenWeatherMapWeather

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String): OpenWeatherMapForecast
}
