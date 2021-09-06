package com.vkornee.weatherapp.weather.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.databinding.CitySelectionFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CitySelectionFragment @Inject constructor(): Fragment(R.layout.city_selection_fragment_layout) {

    private lateinit var binding: CitySelectionFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = requireNotNull(DataBindingUtil.bind(view))
        binding.fragment = this
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.submitBtn.isEnabled = !binding.cityTil.editText?.text.isNullOrBlank()
    }

    fun onLookupClick() {
        val text = binding.cityTil.editText?.text?.toString()
        if (!text.isNullOrBlank()) {
            val capitalized = text.lowercase().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
            (activity as? AppCompatActivity)?.supportActionBar?.title = capitalized
            findNavController().navigate(CitySelectionFragmentDirections.cityToWeatherList(capitalized))
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        binding.submitBtn.isEnabled = s.isNotEmpty()
    }
}