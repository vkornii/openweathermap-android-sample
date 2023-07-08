package com.vkornee.weatherapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Destination {
    val icon: ImageVector
    val route: String
    val args: List<NamedNavArgument>

    object CitySelection : Destination {
        override val icon = Icons.Default.Search
        override val route = "citySelection"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object WeatherDetails : Destination {
        override val icon = Icons.Default.Info
        override val route = "weatherDetails"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object WeatherList : Destination {
        const val CITY_ARG = "city"
        private const val PATH = "weatherList"

        override val icon = Icons.Default.List
        override val route = "${PATH}/{$CITY_ARG}"
        override val args: List<NamedNavArgument> = listOf(
            navArgument(CITY_ARG) {
                type = NavType.StringType
            }
        )

        fun formatRoute(city: String) = "${PATH}/$city"
    }
}
