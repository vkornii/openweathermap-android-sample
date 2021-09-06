package com.vkornee.weatherapp.weather.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vkornee.weatherapp.R
import com.vkornee.weatherapp.databinding.WeatherListItemLayoutBinding
import com.vkornee.weatherapp.weather.domain.model.WeatherData

class WeatherAdapter(
    private val listener: (WeatherData) -> Unit
): ListAdapter<WeatherData, WeatherAdapter.ViewHolder>(WeatherItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item_layout, parent, false))

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) =
        holder.bind(getItem(position), listener)

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = requireNotNull(DataBindingUtil.bind<WeatherListItemLayoutBinding>(view))

        fun bind(data: WeatherData, listener: (WeatherData) -> Unit) {
            with(binding) {
                tempTv.text = "${data.temp}"
                weatherTv.text = data.weather
                binding.root.setOnClickListener { listener(data) }
            }
        }
    }
}

private class WeatherItemDiffCallBack: DiffUtil.ItemCallback<WeatherData>() {
    override fun areItemsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: WeatherData, newItem: WeatherData): Boolean =
        oldItem == newItem
}