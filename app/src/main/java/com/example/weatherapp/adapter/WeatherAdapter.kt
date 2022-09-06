package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.domain.data.Xdayweather.WeatherHourly
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class WeatherAdapter @Inject constructor(
    private val mList: List<WeatherHourly>
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.weather_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        when (item.weather[0].icon) {
            "01d", "01n" -> holder.icon.load(R.drawable.icon_clear_day)
            "02d", "02n" -> holder.icon.load(R.drawable.icon_broken_clouds)
            "03d", "03n" -> holder.icon.load(R.drawable.icon_mostly_cloudy)
            "04d", "04n" -> holder.icon.load(R.drawable.icon_broken_clouds)
            "09d", "09n" -> holder.icon.load(R.drawable.icon_snow_weather)
            "10d", "10n" -> holder.icon.load(R.drawable.icon_rainy_weather)
            "11d", "11n" -> holder.icon.load(R.drawable.icon_storm_weather)
            "13d", "13n" -> holder.icon.load(R.drawable.icon_snow_weather)
            "50d", "50n" -> holder.icon.load(R.drawable.icon_cloudy_weather)
        }
        var string = item.main.temp.toString() + "°C"
        holder.temperature.text = string
        string = item.weather[0].main + ": " + item.weather[0].description
        holder.description.text = string

        string = item.dt_txt.map {
            if(it == ' ')
                '\n'
            else
                it
        }.joinToString("")
        holder.date.text = string
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val icon: ImageView = itemView.findViewById(R.id.item_icon)
        val temperature: TextView = itemView.findViewById(R.id.item_temperature)
        val description: TextView = itemView.findViewById(R.id.item_weather_description)
        val date: TextView = itemView.findViewById(R.id.item_date)
    }
}