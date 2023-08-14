package com.vkornee.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkornee.weatherapp.weather.domain.interactor.HomeCityWeatherUseCase
import com.vkornee.weatherapp.weather.presentation.mappers.WeatherDetailsUiMapper
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeCityWeatherUseCase: HomeCityWeatherUseCase,
    private val weatherDetailsUiMapper: WeatherDetailsUiMapper,
) : ViewModel() {

    private val _viewState: StateFlow<ViewState> = homeCityWeatherUseCase(Unit)
        .map(weatherDetailsUiMapper::map)
        .map<WeatherDetailsUiModel, ViewState>(ViewState::Data)
        .catch { emit(ViewState.Error) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState


    sealed class ViewState {
        class Data(val data: WeatherDetailsUiModel) : ViewState()
        object NoResults : ViewState()
        object Error : ViewState()
        object Loading : ViewState()
    }

}
