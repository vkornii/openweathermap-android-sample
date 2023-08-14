package com.vkornee.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkornee.weatherapp.Destination
import com.vkornee.weatherapp.weather.domain.interactor.FlowCityWeatherUseCase
import com.vkornee.weatherapp.weather.domain.interactor.GetCityWeatherUseCase
import com.vkornee.weatherapp.weather.presentation.mappers.WeatherDetailsUiMapper
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val flowCityWeatherUseCase: FlowCityWeatherUseCase,
    private val weatherDetailsUiMapper: WeatherDetailsUiMapper,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val city = requireNotNull(savedStateHandle.get<String>(Destination.WeatherDetails.CITY_ARG))

    init {
        loadWeather()
    }

    private val _events: Channel<Event> = Channel(Channel.CONFLATED)
    val events = _events.receiveAsFlow()

    private val _viewState: StateFlow<ViewState> = savedStateHandle.getStateFlow<String?>(
        Destination.WeatherDetails.CITY_ARG, null
    )
        .filterNotNull()
        .flatMapLatest { city ->
                    flowCityWeatherUseCase(city)
                    .map(weatherDetailsUiMapper::map)
                    .map(ViewState::Data)
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    private fun loadWeather() = viewModelScope.launch {
        val result = runCatching {
            getCityWeatherUseCase(city)
        }
        with (result) {
            onFailure {
                _events.send(Event.Error)
            }
        }
    }

    sealed class ViewState {
        class Data(val data: WeatherDetailsUiModel) : ViewState()
        object NoResults : ViewState()
        object Loading : ViewState()
    }

    sealed class Event {
        object Error : Event()
    }

    fun onRetryClick() {
        loadWeather()
    }
}
