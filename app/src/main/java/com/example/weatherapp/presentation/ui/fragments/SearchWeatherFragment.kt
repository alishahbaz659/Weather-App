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
import com.example.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.example.weatherapp.databinding.FragmentSearchWeatherBinding
import com.example.weatherapp.presentation.viewModel.CurrentLocationWeatherViewModel

import com.example.weatherapp.presentation.viewModel.CurrentWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class SearchWeatherFragment : Fragment() {
    private var _binding: FragmentSearchWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrentWeatherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchWeatherBinding.inflate(inflater, container, false)

        binding.submitButton.setOnClickListener {
            viewModel.getNewWeather(binding.searchingField.text.toString())
            binding.searchingField.isCursorVisible = false
            binding.submitButton.isEnabled = false
        }


        binding.toFiveDayWeatherButton.setOnClickListener {
            val action =
                SearchWeatherFragmentDirections.actionSearchWeatherFragmentToXWeatherFragment(
                    binding.searchingField.text.toString()
                )
            findNavController().navigate(action)
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
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
                binding.country.text = weather.name
                var string = weather.main.temp.toString() + "°C"
                binding.temperature.text = (string)
                string = weather.weather[0].main + ": " + weather.weather[0].description
                binding.weatherDescription.text = (string)
                string =
                    "Max: " + weather.main.tempMax.toString() + "°C / Min: " + weather.main.tempMin + "°C"
                binding.maxMinTemperature.text = (string)
                string =
                    "Pressure: " + weather.main.pressure.toString() + " hPa\nHumidity: " + weather.main.humidity.toString() + "%\nWind speed: " + weather.wind.speed.toString() + " meter/sec\nClouds: " + weather.clouds.all.toString() + "%"
                binding.otherInformation.text = string
                binding.icon.visibility = View.VISIBLE
                binding.country.visibility = View.VISIBLE
                binding.temperature.visibility = View.VISIBLE
                binding.weatherDescription.visibility = View.VISIBLE
                binding.maxMinTemperature.visibility = View.VISIBLE
                binding.otherInformation.visibility = View.VISIBLE
                binding.submitButton.isEnabled = true
            }
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Search Weather"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}