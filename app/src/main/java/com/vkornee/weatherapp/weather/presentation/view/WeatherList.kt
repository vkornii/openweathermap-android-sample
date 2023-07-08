package com.vkornee.weatherapp.weather.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import com.vkornee.weatherapp.weather.presentation.viewmodel.WeatherListViewModel

@Composable
fun WeatherList(
    viewModel: WeatherListViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsState()

    when (val state = uiState) {
        is WeatherListViewModel.ViewState.NoResults -> NoResults()
        is WeatherListViewModel.ViewState.Data -> Content(state.data)
        is WeatherListViewModel.ViewState.Error -> ErrorScreen(
            recoverable = state.recoverable
        ) {
            viewModel.onRetryClick()
        }
        is WeatherListViewModel.ViewState.Loading -> Loading()
    }
}

@Composable
fun Content(data: WeatherDetailsUiModel) {
    WeatherItem(data)
}

@Composable
fun WeatherItem(weatherData: WeatherDetailsUiModel) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(16.dp)
    ) {
        Column {
            Text(text = "id = ${weatherData.header.location}")
            Text(text = "weather = ${weatherData.header.description}")
            Text(text = "temp = ${weatherData.header.temp}")
            Text(text = "feelsLikeTemp = ${weatherData.header.feelsLike}")
            Text(text = "description = ${weatherData.header.dateTime}")
        }
    }
}

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NoResults() {

}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}
