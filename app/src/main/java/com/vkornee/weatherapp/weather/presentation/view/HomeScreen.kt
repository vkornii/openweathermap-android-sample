package com.vkornee.weatherapp.weather.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vkornee.weatherapp.weather.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onLocationNotSelected: () -> Unit
) {
    val uiState = viewModel.viewState.collectAsState()

    when (val state = uiState.value) {
        is HomeViewModel.ViewState.NoResults -> NoResults()
        is HomeViewModel.ViewState.Data -> Content(state.data)
        is HomeViewModel.ViewState.Loading -> Loading()
        is HomeViewModel.ViewState.Error -> {
            LaunchedEffect(Unit) { onLocationNotSelected() }
        }
    }

}
