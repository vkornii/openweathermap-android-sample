package com.vkornee.weatherapp.weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vkornee.weatherapp.Destination
import com.vkornee.weatherapp.weather.presentation.view.CitySelection
import com.vkornee.weatherapp.weather.presentation.view.WeatherDetails
import com.vkornee.weatherapp.weather.presentation.view.WeatherList

@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destination.CitySelection.route,
        modifier = modifier
    ) {
        composable(Destination.CitySelection.route) {
            CitySelection { city ->
                navController.navigate(Destination.WeatherList.formatRoute(city))
            }
        }
        composable(Destination.WeatherDetails.route) {
            WeatherDetails()
        }
        composable(
            route = Destination.WeatherList.route,
            arguments = Destination.WeatherList.args
        ) {
            WeatherList()
        }
    }
}
