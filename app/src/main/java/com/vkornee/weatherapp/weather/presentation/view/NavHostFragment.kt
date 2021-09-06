package com.vkornee.weatherapp.weather.presentation.view

import androidx.annotation.NavigationRes
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherNavHostFragment: NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: DefaultFragmentFactory

    companion object {
        fun create(@NavigationRes graphResId: Int): NavHostFragment {
            return WeatherNavHostFragment().apply {
                arguments = bundleOf(
                    "android-support-nav:fragment:graphId" to graphResId
                )
            }
        }
    }

}