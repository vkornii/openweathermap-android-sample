package com.vkornee.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkornee.weatherapp.weather.domain.interactor.SelectCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val selectCityUseCase: SelectCityUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Content)
    val state: StateFlow<ViewState> = _state

    fun onSubmit(city: String) {
        _state.value = ViewState.Loading
        viewModelScope.launch {
            selectCityUseCase(city)
            _state.value = ViewState.CitySubmitted(city)
        }
    }

    sealed class ViewState {
        object Content : ViewState()
        object Loading : ViewState()
        class CitySubmitted(val city: String) : ViewState()
    }
}
