package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.CoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.repository.ISettingsRepository
import javax.inject.Inject

class SelectCityUseCase @Inject constructor(
    private val repository: ISettingsRepository,
    dispatchersProvider: DispatchersProvider
): CoroutineUseCase<String, Unit>(dispatchersProvider.default) {
    override suspend fun execute(params: String) {
        repository.saveCity(params)
    }

}
