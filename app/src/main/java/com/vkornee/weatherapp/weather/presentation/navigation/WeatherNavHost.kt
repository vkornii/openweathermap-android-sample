package com.vkornee.weatherapp.weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vkornee.weatherapp.Destination
import com.vkornee.weatherapp.weather.presentation.view.CitySelectionScreen
import com.vkornee.weatherapp.weather.presentation.view.HomeScreen
import com.vkornee.weatherapp.weather.presentation.view.WeatherScreen

@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onLocationNotSelected = { navController.navigate(Destination.CitySelection.route) }
            )
        }
        composable(Destination.CitySelection.route) {
            CitySelectionScreen { city ->
                with (navController) {
                    popBackStack()
                    navController.navigate(Destination.WeatherDetails.formatRoute(city))
                }
            }
        }
        composable(
            route = Destination.WeatherDetails.route,
            arguments = Destination.WeatherDetails.args
        ) {
            WeatherScreen()
        }
    }
}
