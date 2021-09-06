package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.FlowCoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.domain.repository.IWeatherRepository
import com.vkornee.weatherapp.weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    @Repository
    private val repository: IWeatherRepository,
    dispatchersProvider: DispatchersProvider
): FlowCoroutineUseCase<GetWeatherDetailsUseCase.Params, WeatherData>(dispatchersProvider.io) {

    override fun execute(params: Params): Flow<WeatherData> =
        repository.getWeatherDetails(params.city, params.weatherId)

    data class Params(
        val city: String,
        val weatherId: Int)
}