package com.example.weatherapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.FavouriteCitiesWeatherAdapter
import com.example.weatherapp.databinding.FragmentFavouriteCitiesBinding
import com.example.weatherapp.domain.adapter.AdapterInterface
import com.example.weatherapp.presentation.viewModel.FavouriteCitiesWeatherViewModel


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteCitiesWeatherFragment : Fragment(), AdapterInterface {
    private var _binding: FragmentFavouriteCitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteCitiesWeatherViewModel by viewModels()

    lateinit var adapter: FavouriteCitiesWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteCitiesBinding.inflate(inflater, container, false)
        binding.favCitiesRecyclerview.layoutManager = LinearLayoutManager(context)

        viewModel.loadRecords()

        viewModel.getRecordsObserver().observe(viewLifecycleOwner) { weather ->
            adapter = FavouriteCitiesWeatherAdapter(weather, this)
            binding.favCitiesRecyclerview.adapter = adapter
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Favourite Cities"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun OnClickListener(position: Int, cityName: String, view: View) {
        val action =
            FavouriteCitiesWeatherFragmentDirections.actionFavouriteCitiesWeatherFragmentToWeatherDetailViewFragment(
                cityName
            )
        findNavController().navigate(action)
    }


}