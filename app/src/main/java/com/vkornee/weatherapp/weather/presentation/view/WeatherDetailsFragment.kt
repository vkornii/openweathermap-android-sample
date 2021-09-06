package com.vkornee.weatherapp.weather.presentation.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.common.observeInLifecycle
import com.vkornee.weatherapp.databinding.WeatherDetailsFragmentLayoutBinding
import com.vkornee.weatherapp.databinding.WeatherListFragmentLayoutBinding
import com.vkornee.weatherapp.weather.domain.model.WeatherData
import com.vkornee.weatherapp.weather.presentation.viewmodel.WeatherDetailsViewModel
import com.vkornee.weatherapp.weather.presentation.viewmodel.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class WeatherDetailsFragment @Inject constructor(): Fragment(R.layout.weather_details_fragment_layout) {
    private val args by navArgs<WeatherDetailsFragmentArgs>()
    private val viewModel: WeatherDetailsViewModel by viewModels()

    private lateinit var binding: WeatherDetailsFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = requireNotNull(DataBindingUtil.bind(view))

        viewModel.observe(args.city, args.detailsId)
            .onEach(::updateUI)
            .observeInLifecycle(viewLifecycleOwner)
    }

    private fun updateUI(data: WeatherData) {
        with (binding) {
            tempTv.text = "${data.temp}"
            feelsTempTv.text = getString(R.string.feels_like, data.feelsLikeTemp)
            weatherTv.text = data.weather
            description.text = data.description
        }
    }
}