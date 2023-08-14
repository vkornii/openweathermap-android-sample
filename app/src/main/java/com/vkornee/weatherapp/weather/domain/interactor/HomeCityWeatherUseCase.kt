package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.FlowCoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.LocationNotSelectedException
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.repository.ISettingsRepository
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class HomeCityWeatherUseCase @Inject constructor(
    private val settingsRepository: ISettingsRepository,
    private val repository: IWeatherRepository,
    dispatchersProvider: DispatchersProvider
): FlowCoroutineUseCase<Unit, WeatherData>(dispatchersProvider.io) {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun execute(params: Unit): Flow<WeatherData> =
        settingsRepository.selectedCityFlow()
            .map { city ->
                city ?: throw LocationNotSelectedException()
            }
            .flatMapLatest { city ->
                merge(
                    flowOf(repository.getWeatherData(city)),
                    repository.flowCityWeather(city)
                )
            }
}
