package com.example.openweather.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.repo.WeatherRepository
import com.example.openweather.utils.Constants
import kotlinx.coroutines.launch

class CurrentWeatherByCityViewModel :ViewModel() {

    var weatherLiveData = MutableLiveData<CurrentWeatherResponse>()
    private val repo = WeatherRepository()

    fun getWeatherByCity(q: String, apikey: String, unit: String ) = viewModelScope.launch {
        val response =  repo.getCurrentWeatherByCity(q, apikey = Constants.API_KEY ,  unit  )
        weatherLiveData.postValue(response.body())
    }

}