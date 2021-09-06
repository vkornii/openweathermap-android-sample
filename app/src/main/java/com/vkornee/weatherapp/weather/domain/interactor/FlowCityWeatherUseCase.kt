package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.FlowCoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import com.vkornee.weatherapp.weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowCityWeatherUseCase @Inject constructor(
    @Repository
    private val repository: IWeatherRepository,
    dispatchersProvider: DispatchersProvider
): FlowCoroutineUseCase<String, List<WeatherData>>(dispatchersProvider.default) {
    override fun execute(params: String): Flow<List<WeatherData>> =
        repository.flowCityWeather(params)
}