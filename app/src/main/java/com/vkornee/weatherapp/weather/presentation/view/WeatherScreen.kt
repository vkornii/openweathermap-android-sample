package com.vkornee.weatherapp.weather.presentation.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.weather.presentation.model.WeatherDetailsUiModel
import com.vkornee.weatherapp.weather.presentation.model.WeatherPeriod
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

   Surface(
       color = MaterialTheme.colorScheme.background
   ) {
       when (val state = uiState) {
           is WeatherDetailsViewModel.ViewState.NoResults -> NoResults()
           is WeatherDetailsViewModel.ViewState.Data -> Content(state.data)
           is WeatherDetailsViewModel.ViewState.Loading -> Loading()
       }
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
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Header(data)
            PeriodSelection(data)
            Tiles(data)
            WeatherGraph(
                Modifier
                    .height(150.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                        shape = MaterialTheme.shapes.small
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
private fun PeriodSelection(
    data: WeatherDetailsUiModel,
    onPeriodSelected: (WeatherPeriod) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        //TODO remove this, only for debugging ui
        var selectedPeriod by remember { mutableStateOf(data.selectedPeriod) }
        WeatherPeriod.values().forEach {
            val isSelected = selectedPeriod == it
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondaryContainer
                ),
                shape = MaterialTheme.shapes.small,
                onClick = {
                    selectedPeriod = it
                    onPeriodSelected(it)
                }
            ) {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSelected)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSecondaryContainer
                    )
            }
        }
    }
}

@Composable
private fun Tiles(data: WeatherDetailsUiModel) {
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
}

@Composable
private fun Header(
    weatherData: WeatherDetailsUiModel,
    onSearchClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 194.dp),
        tonalElevation = 6.dp,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = weatherData.header.location,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = "10:59",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = weatherData.header.temp,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .width(100.dp)
                    .height(100.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.clear_day)
                    .build(),
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                contentDescription = null
            )
            Button(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopEnd),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = onSearchClick
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            }
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
    Surface(
        modifier = modifier,
        tonalElevation = 6.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
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
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = data,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
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
