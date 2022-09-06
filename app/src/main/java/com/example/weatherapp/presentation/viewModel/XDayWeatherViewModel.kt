package com.example.weatherapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.domain.data.Xdayweather.XDayWeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class XDayWeatherViewModel @Inject constructor(private val dataSource: WeatherRepository): ViewModel() {
    var currentWeather: MutableLiveData<XDayWeatherResponse> = MutableLiveData<XDayWeatherResponse>()

    var cityName: MutableLiveData<String> = MutableLiveData()

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeather.postValue(dataSource.getXDayWeather(cityName.value!!))
        }
    }

    fun setCityName(city: String) {
        cityName.value = city
    }
}