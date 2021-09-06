package com.vkornee.weatherapp.weather.presentation.di

import androidx.fragment.app.Fragment
import com.vkornee.weatherapp.weather.presentation.view.CitySelectionFragment
import com.vkornee.weatherapp.weather.presentation.view.WeatherDetailsFragment
import com.vkornee.weatherapp.weather.presentation.view.WeatherListFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class FragmentFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(CitySelectionFragment::class)
    abstract fun bindCitySelectionFragment(fragment: CitySelectionFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(WeatherListFragment::class)
    abstract fun bindWeatherListFragment(fragment: WeatherListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(WeatherDetailsFragment::class)
    abstract fun bindWeatherDetailsFragment(fragment: WeatherDetailsFragment): Fragment
}