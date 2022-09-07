package com.example.weatherapp.presentation.ui.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.localDatabase.WeatherEntity
import com.example.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.example.weatherapp.presentation.viewModel.CurrentLocationWeatherViewModel
import com.example.weatherapp.presentation.viewModel.FavouriteCitiesWeatherViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModelCurrentLocation: CurrentLocationWeatherViewModel by viewModels()
    private val viewModelFavCities: FavouriteCitiesWeatherViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var currentLocationName: String
    private var currentLocationTemprature: Double =0.0
    lateinit var convertToCelsius:MenuItem
    lateinit var convertToFahrenheit:MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("DEBUG", "${it.key} = ${it.value}")
            }
            viewModelCurrentLocation.getCurrentLocationWeather()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)

        binding.toFiveDayWeatherButton.setOnClickListener {
            val action =
                CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToFiveDayWeatherFragment(
                    currentLocationName
                )
            findNavController().navigate(action)
        }

        binding.addToFavButton.setOnClickListener {
            val weatherEntity = WeatherEntity(name = currentLocationName)
            viewModelFavCities.insertRecord(weatherEntity)
            binding.addToFavButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }


        viewModelCurrentLocation.currentLocationWeather.observe(viewLifecycleOwner) { weather ->
            if (weather != null) {
                    when (weather.weather[0].icon) {
                        "01d", "01n" -> binding.icon.load(R.drawable.icon_clear_day)
                        "02d", "02n" -> binding.icon.load(R.drawable.icon_broken_clouds)
                        "03d", "03n" -> binding.icon.load(R.drawable.icon_mostly_cloudy)
                        "04d", "04n" -> binding.icon.load(R.drawable.icon_broken_clouds)
                        "09d", "09n" -> binding.icon.load(R.drawable.icon_snow_weather)
                        "10d", "10n" -> binding.icon.load(R.drawable.icon_rainy_weather)
                        "11d", "11n" -> binding.icon.load(R.drawable.icon_storm_weather)
                        "13d", "13n" -> binding.icon.load(R.drawable.icon_snow_weather)
                        "50d", "50n" -> binding.icon.load(R.drawable.icon_cloudy_weather)
                    }
                    currentLocationName = weather.name
                    currentLocationTemprature = weather.main.temp


                    binding.country.text = weather.name
                    var string = weather.main.temp.toString() + "°C"
                    binding.temperature.text = (string)
                    string = weather.weather[0].main + ": " + weather.weather[0].description
                    binding.weatherDescription.text = (string)
                    string =
                        "Max: " + weather.main.tempMax.toString() + "°C / Min: " + weather.main.tempMin + "°C"
                    binding.maxMinTemperature.text = (string)
                    binding.weatherPressue.text =
                        "Pressure: " + weather.main.pressure.toString() + "hPa"
                    binding.weatherHumidity.text =
                        "Humidity: " + weather.main.humidity.toString() + "%"
                    binding.weatherWindSpeed.text =
                        "Wind speed: " + weather.wind.speed.toString() + "meter/sec"
                    binding.weatherClouds.text = "Clouds: " + weather.clouds.all.toString() + "%"

                    binding.toFiveDayWeatherButton.isEnabled = true
                    binding.toFiveDayWeatherButton.isClickable = true
                    binding.addToFavButton.visibility = View.VISIBLE
                }
            }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        convertToCelsius=menu.findItem(R.id.action_convertToCelsius)
        convertToFahrenheit=menu.findItem(R.id.action_convertToFahrenheit)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_search -> {
                val action =
                    CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSearchWeatherFragment(
                        ""
                    )
                findNavController().navigate(action)
            }
            R.id.action_convertToCelsius -> {
                val temp = f2c(currentLocationTemprature)
                currentLocationTemprature=temp
                binding.temperature.text = (Math.round(temp * 100.0) / 100.0).toString() + "°C"
                convertToCelsius.isEnabled=false
                convertToFahrenheit.isEnabled=true

            }
            R.id.action_convertToFahrenheit -> {
                val temp = c2f(currentLocationTemprature)
                currentLocationTemprature=temp
                binding.temperature.text = (Math.round(temp * 100.0) / 100.0).toString() + "°F"
                convertToCelsius.isEnabled=true
                convertToFahrenheit.isEnabled=false

            }

            R.id.action_fav -> {
                val action =
                    CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToFavCityFragment()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun c2f(temp: Double): Double {
        return (temp * 9 / 5) + 32
    }

    fun f2c(temp: Double): Double {
        return (temp - 32) * 5 / 9
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Current weather"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}