package com.example.weatherapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.data.currentweather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentLocationWeatherViewModel @Inject constructor(
    private val dataSource: WeatherRepository,
    private val locationTracker: LocationTracker
) :
    ViewModel() {
    var currentLocationWeather: MutableLiveData<WeatherResponse> =
        MutableLiveData<WeatherResponse>()

    fun getCurrentLocationWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            locationTracker.getCurrentLocation()?.let { location ->
                currentLocationWeather.postValue(
                    dataSource.getCurrentLocationWeather(
                        location.latitude,
                        location.longitude
                    )
                )
            }

        }
    }
}