package com.example.weatherapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentXDayWeatherBinding
import com.example.weatherapp.presentation.viewModel.XDayWeatherViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class XDayWeatherFragment : Fragment() {
    private var _binding: FragmentXDayWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: XDayWeatherViewModel by viewModels()

    private val args: XDayWeatherFragmentArgs by navArgs()

    lateinit var adapter: WeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentXDayWeatherBinding.inflate(inflater, container, false)
        binding.xDayRecyclerview.layoutManager = LinearLayoutManager(context)

        viewModel.setCityName(args.cityName)
        viewModel.cityName.observe(viewLifecycleOwner) {
            viewModel.getWeather()
        }

        viewModel.currentWeather.observe(viewLifecycleOwner) { weather ->
            adapter = WeatherAdapter(weather.list)
            binding.xDayRecyclerview.adapter = adapter
            binding.cityLabel.text = weather.city.name
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Weather Forecast"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}