package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.FlowCoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowCityWeatherUseCase @Inject constructor(
    private val repository: IWeatherRepository,
    dispatchersProvider: DispatchersProvider
): FlowCoroutineUseCase<String, WeatherData>(dispatchersProvider.default) {
    override fun execute(params: String): Flow<WeatherData> =
        repository.flowCityWeather(params)
}
