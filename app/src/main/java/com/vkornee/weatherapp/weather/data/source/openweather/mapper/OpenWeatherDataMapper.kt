package com.vkornee.weatherapp.weather.data.source.openweather.mapper

import com.vkornee.weatherapp.common.BaseMapper
import com.vkornee.weatherapp.weather.data.source.openweather.model.OpenWeatherMapWeather
import com.vkornee.weatherapp.weather.domain.model.Location
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.model.Wind
import javax.inject.Inject

class OpenWeatherDataMapper @Inject constructor()
    : BaseMapper<OpenWeatherMapWeather, WeatherData>() {

    override fun map(from: OpenWeatherMapWeather): WeatherData =
        WeatherData(
            location = Location(
                id = from.id,
                city = from.name,
                country = null,
            ),
            temp = from.main.temp,
            feelsLike = from.main.feels_like,
            minTemp = from.main.temp_min,
            maxTemp = from.main.temp_max,
            description = from.weather.firstOrNull()?.description.orEmpty(),
            wind = Wind(
                from.wind.gust,
                from.wind.deg,
                from.wind.speed,
            ),
            cloudsPercent = from.clouds.all,
            pressure = from.main.pressure,
        )
}
