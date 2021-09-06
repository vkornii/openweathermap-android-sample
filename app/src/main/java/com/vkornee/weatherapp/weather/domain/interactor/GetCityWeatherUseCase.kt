package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.CoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import com.vkornee.weatherapp.weather.domain.repository.Repository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    @Repository
    private val repository: IWeatherRepository,
    dispatchersProvider: DispatchersProvider
): CoroutineUseCase<String, List<WeatherData>>(dispatchersProvider.io) {
    override suspend fun execute(params: String): List<WeatherData> =
        repository.getWeatherData(params)
}