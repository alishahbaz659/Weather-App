package com.example.weatherapp.presentation.ui.fragments

import com.google.common.truth.Truth
import org.junit.Test

class SearchWeatherFragmentTest{

    @Test
    fun `empty cityName returns false` (){
        var searchWeatherFragment=SearchWeatherFragment()
        val result= searchWeatherFragment.validateInput("")
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `not Empty cityName returns true` (){
        var searchWeatherFragment=SearchWeatherFragment()
        val result= searchWeatherFragment.validateInput("UAE")
        Truth.assertThat(result).isTrue()
    }
}