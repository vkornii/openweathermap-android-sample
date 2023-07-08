package com.vkornee.weatherapp.weather.data.source.openweather.model

/* Generated with JSON to Kotlin */
data class OpenWeatherMapForecast(
    val city: CityDto,
    val cnt: Int,
    val cod: String,
    val list: List<OpenWeatherMapWeather>,
    val message: Int
)

data class CityDto(
    val coord: CoordDto,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)

data class CoordDto(
    val lat: Double,
    val lon: Double
)

data class CloudsDto(
    val all: Int
)

data class MainDto(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class RainDto(
    val `3h`: Double
)

data class SysDto(
    val pod: String
)

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class WindDto(
    val deg: Int,
    val gust: Double,
    val speed: Double
)

data class OpenWeatherMapWeather(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    val coord: CoordDto,
    val dt: Int,
    val id: Int,
    val main: MainDto,
    val name: String,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)
