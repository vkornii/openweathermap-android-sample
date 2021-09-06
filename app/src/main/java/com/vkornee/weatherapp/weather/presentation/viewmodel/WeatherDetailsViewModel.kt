package com.vkornee.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vkornee.weatherapp.weather.domain.interactor.GetWeatherDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    fun observe(city: String, detailsId: Int) =
        getWeatherDetailsUseCase(GetWeatherDetailsUseCase.Params(city, detailsId))
}