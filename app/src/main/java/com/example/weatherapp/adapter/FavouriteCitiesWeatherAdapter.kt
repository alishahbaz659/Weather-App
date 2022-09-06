package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.localDatabase.WeatherEntity
import com.example.weatherapp.domain.adapter.AdapterInterface
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FavouriteCitiesWeatherAdapter @Inject constructor(
    private val mList: List<WeatherEntity>,
    private val myInterface: AdapterInterface
) : RecyclerView.Adapter<FavouriteCitiesWeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.weather_favourite_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.cityName.text = item.name

        holder.itemView.setOnClickListener{
            myInterface.OnClickListener(position = position, cityName = item.name,it)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cityName: TextView = itemView.findViewById(R.id.city_name)
    }
}