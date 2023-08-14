package com.vkornee.weatherapp.weather.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.vkornee.weatherapp.weather.presentation.viewmodel.CitySelectionViewModel

@Composable
fun CitySelectionScreen(
    viewModel: CitySelectionViewModel = hiltViewModel(),
    onSubmit: (String) -> Unit
) {
    val state = viewModel.state.collectAsState()

    when (val viewState = state.value) {
        CitySelectionViewModel.ViewState.Content -> CitySelection(viewModel)
        CitySelectionViewModel.ViewState.Loading -> CitySelection(viewModel, true)
        is CitySelectionViewModel.ViewState.CitySubmitted -> {
            LaunchedEffect(key1 = viewState.city) { onSubmit(viewState.city) }
        }
    }
}

@Composable
private fun CitySelection(
    viewModel: CitySelectionViewModel,
    isLoading: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current
        var city by remember { mutableStateOf("") }
        TextField(
            value = city,
            onValueChange = { city = it },
            enabled = isLoading.not(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedTextColor = MaterialTheme.colorScheme.primaryContainer,
                errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        Button(
            enabled = isLoading.not(),
            onClick = {
                focusManager.clearFocus()
                viewModel.onSubmit(city)
            }
        ) {
            Text(text = "Submit")
        }
    }
}


@Preview
@Composable
fun PreviewCitySelection() {
    CitySelectionScreen(onSubmit = {})
}
