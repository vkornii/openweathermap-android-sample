package com.vkornee.weatherapp.weather.presentation.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import com.vkornee.weatherapp.weather.presentation.model.WeekForecastGraphData
import com.vkornee.weatherapp.weather.presentation.model.WeekForecastGraphEntry
import com.vkornee.weatherapp.weather.presentation.viewmodel.WeatherDetailsViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.datetime.Clock
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun WeatherScreen(
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.viewState.collectAsState()

    when (val state = uiState) {
        is WeatherDetailsViewModel.ViewState.NoResults -> NoResults()
        is WeatherDetailsViewModel.ViewState.Data -> Content(state.data)
        is WeatherDetailsViewModel.ViewState.Loading -> Loading()
    }

    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier)

    LaunchedEffect(key1 = Unit) {
        viewModel.events.collectLatest {
            when (it) {
                WeatherDetailsViewModel.Event.Error -> {
                    snackState.showSnackbar(
                        message = "Error"
                    )
                }
            }
        }
    }
}

@Composable
fun Content(data: WeatherDetailsUiModel) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Header(data)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeatherInfoTile(
                    modifier = Modifier
                        .weight(1f),
                    title = "Wind",
                    data = "10ms",
                    icon = R.drawable.ic_wind
                )
                WeatherInfoTile(
                    modifier = Modifier
                        .weight(1f),
                    title = "Pressure",
                    data = "10ms",
                    icon = R.drawable.ic_wind
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeatherInfoTile(
                    modifier = Modifier.weight(1f),
                    title = "Rain Chance",
                    data = "10ms",
                    icon = R.drawable.ic_wind
                )
                WeatherInfoTile(
                    modifier = Modifier.weight(1f),
                    title = "UV Index",
                    data = "10ms",
                    icon = R.drawable.ic_wind
                )
            }
            WeatherGraph(
                Modifier
                    .height(150.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),
                data = WeekForecastGraphData(
                entries = listOf(-17, -5, 0, -3, 5, -15, 15).mapIndexed { i, v ->
                    WeekForecastGraphEntry(
                        date = Clock.System.now().plus(i.toDuration(DurationUnit.DAYS)),
                        temp = v,
                        isNow = false
                    )
                }.toImmutableList()
            ))
        }
    }
}

@Composable
private fun Header(weatherData: WeatherDetailsUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(16.dp),
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
private fun WeatherInfoTile(
    modifier: Modifier = Modifier,
    title: String,
    data: String,
    @DrawableRes icon: Int
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .padding(2.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                )
                .padding(2.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(icon)
                .build(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
            contentDescription = null
        )
        Column {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
            Text(
                text = data,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    }
}

@Composable
fun NoResults() { }


@Preview
@Composable
fun WeatherInfoTilePreview() {
    WeatherInfoTile(title = "Wind", data = "10ms", icon = R.drawable.ic_wind)
}
