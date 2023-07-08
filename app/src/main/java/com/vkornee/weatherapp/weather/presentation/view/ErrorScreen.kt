package com.vkornee.weatherapp.weather.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    recoverable: Boolean,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.errorContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        )
        if (recoverable) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                onClick = onRetryClick
            ) {
                Text(
                    text = "Retry",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onError
                    )
                )
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    val errorScreenSystemBarColor = MaterialTheme.colorScheme.errorContainer
    val defaultSystemBarColor = MaterialTheme.colorScheme.primary
    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = errorScreenSystemBarColor
        )
        onDispose {
            systemUiController.setSystemBarsColor(
                color = defaultSystemBarColor
            )
        }
    }
}

@Preview
@Composable
fun ErrorRecoverablePreview() {
    ErrorScreen(
        recoverable = true
    ) { }
}

@Preview
@Composable
fun ErrorNotRecoverablePreview() {
    ErrorScreen(
        recoverable = false
    ) { }
}
