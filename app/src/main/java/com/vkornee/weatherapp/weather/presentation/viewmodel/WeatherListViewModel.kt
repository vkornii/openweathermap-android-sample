package com.vkornee.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkornee.weatherapp.weather.domain.interactor.FlowCityWeatherUseCase
import com.vkornee.weatherapp.weather.domain.interactor.GetCityWeatherUseCase
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val flowCityWeatherUseCase: FlowCityWeatherUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: Flow<ViewState> = _viewState

    sealed class ViewState {
        class Data(val data: List<WeatherData>): ViewState()
        class Error(val err: Throwable): ViewState()
        object Loading: ViewState()
    }

    fun observe(city: String) {
        viewModelScope.launch {
            launch {
                val result = runCatching { getCityWeatherUseCase(city) }
                with (result) {
                    onFailure {
                        _viewState.value = ViewState.Error(it)
                    }
                }
            }
            flowCityWeatherUseCase(city)
                .map { ViewState.Data(it) }
                .collect {
                    _viewState.value = it
                }
        }
    }
}