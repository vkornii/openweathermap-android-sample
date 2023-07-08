package com.vkornee.weatherapp.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkornee.weatherapp.Destination
import com.vkornee.weatherapp.weather.domain.interactor.FlowCityWeatherUseCase
import com.vkornee.weatherapp.weather.domain.interactor.GetCityWeatherUseCase
import com.vkornee.weatherapp.weather.presentation.mappers.WeatherDetailsUiMapper
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val flowCityWeatherUseCase: FlowCityWeatherUseCase,
    private val weatherDetailsUiMapper: WeatherDetailsUiMapper,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val cityArg: String? = savedStateHandle[Destination.WeatherList.CITY_ARG]

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    init {
        if (cityArg == null) {
            _viewState.value = ViewState.Error(recoverable = false)
        } else {
            observe(cityArg)
        }
    }

    sealed class ViewState {
        class Data(val data: WeatherDetailsUiModel) : ViewState()
        class Error(val recoverable: Boolean) : ViewState()
        object NoResults : ViewState()
        object Loading : ViewState()
    }

    private fun observe(city: String) {
        viewModelScope.launch {
            launch {
                runCatching { getCityWeatherUseCase(city) }
                    .onFailure {
                        Log.w("kornienko", "$it")
                        it.printStackTrace()
                        _viewState.value = ViewState.Error(true)
                    }
            }
            flowCityWeatherUseCase(city)
                .map {
                    ViewState.Data(weatherDetailsUiMapper.map(it))
                }
                .collect {_viewState.value = it }
        }
    }

    fun onRetryClick() { }
}
