package com.vkornee.weatherapp.weather.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.common.observeInLifecycle
import com.vkornee.weatherapp.databinding.WeatherListFragmentLayoutBinding
import com.vkornee.weatherapp.weather.domain.model.UnknownCityException
import com.vkornee.weatherapp.weather.presentation.viewmodel.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.appcompat.app.AppCompatActivity





@AndroidEntryPoint
class WeatherListFragment @Inject constructor(): Fragment(R.layout.weather_list_fragment_layout) {

    private val args by navArgs<WeatherListFragmentArgs>()
    private val viewModel: WeatherListViewModel by viewModels()
    private val weatherAdapter = WeatherAdapter { weatherData ->
        findNavController().navigate(
            WeatherListFragmentDirections.cityToWeatherList(args.city, weatherData.id)
        )
    }

    private lateinit var binding: WeatherListFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = requireNotNull(DataBindingUtil.bind(view))
        with (binding.weatherRv) {
            adapter = weatherAdapter
            ContextCompat.getDrawable(requireContext(), R.drawable.item_divider)?.let { drawable ->
                val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                itemDecorator.setDrawable(drawable)
                addItemDecoration(itemDecorator)
            }
        }

        (activity as? AppCompatActivity)?.supportActionBar?.show()

        viewModel.viewState
            .onEach(::setViewState)
            .observeInLifecycle(viewLifecycleOwner)

        viewModel.observe(args.city)
    }

    private suspend fun setViewState(viewState: WeatherListViewModel.ViewState) {
        when (viewState) {
            is WeatherListViewModel.ViewState.Data -> {
                binding.progress.isVisible = false
                binding.weatherRv.isVisible = true
                weatherAdapter.submitList(viewState.data)
            }
            is WeatherListViewModel.ViewState.Error -> {
                binding.progress.isVisible = false
                val err = viewState.err
                if(err is UnknownCityException) {
                    val message = getString(R.string.unknown_city, err.city)
                    Toast.makeText(context, message, Toast.LENGTH_LONG)?.show()
                }
                withContext(Dispatchers.Default) { delay(2000) }
                findNavController().navigateUp()

            }
            is WeatherListViewModel.ViewState.Loading -> {
                binding.progress.isVisible = true
                binding.weatherRv.isVisible = false
            }
        }
    }
}