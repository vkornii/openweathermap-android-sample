package com.vkornee.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.navigation.compose.rememberNavController
import com.vkornee.weatherapp.weather.presentation.navigation.WeatherNavHost
import com.vkornee.weatherapp.weather.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(
                dynamicColor = false
            ) {
                WeatherApp()
            }
        }
    }

    @Composable
    fun WeatherApp() {
        WeatherNavHost()
    }
}
