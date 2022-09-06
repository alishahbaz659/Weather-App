package com.example.weatherapp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.localDatabase.WeatherEntity
import com.example.weatherapp.data.repository.RoomRepository
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.data.currentweather.WeatherResponse
import com.example.weatherapp.domain.Weather.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteCitiesWeatherViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) :
    ViewModel() {
    var userData: MutableLiveData<List<WeatherEntity>> = MutableLiveData()


    fun getRecordsObserver(): MutableLiveData<List<WeatherEntity>> {
        return userData
    }

    fun loadRecords() {
        val list = roomRepository.getRecords()
        userData.postValue(list)
    }

    fun insertRecord(weatherEntity: WeatherEntity) {
        roomRepository.insertRecord(weatherEntity)
        loadRecords()
    }
}