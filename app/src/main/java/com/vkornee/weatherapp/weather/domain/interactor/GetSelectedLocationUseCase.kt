package com.vkornee.weatherapp.weather.domain.interactor

import com.vkornee.weatherapp.common.FlowCoroutineUseCase
import com.vkornee.weatherapp.common.dispatchers.DispatchersProvider
import com.vkornee.weatherapp.weather.domain.model.LocationSelection
import com.vkornee.weatherapp.weather.domain.repository.ISettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedLocationUseCase @Inject constructor(
    private val settingsRepository: ISettingsRepository,
    dispatchersProvider: DispatchersProvider
): FlowCoroutineUseCase<Unit, LocationSelection>(dispatchersProvider.default) {
    override fun execute(params: Unit): Flow<LocationSelection> =
        settingsRepository.selectedCityFlow()
            .map { it?.let(LocationSelection::City) ?: LocationSelection.NotSelected }
}
