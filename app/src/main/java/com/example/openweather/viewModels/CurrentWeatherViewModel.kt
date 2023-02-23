package com.example.openweather.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.repo.WeatherRepository
import com.example.openweather.data.models.CurrentWeatherResponse
import kotlinx.coroutines.launch

class CurrentWeatherViewModel :ViewModel() {

    var weatherLiveData = MutableLiveData<CurrentWeatherResponse>()
    private val repo = WeatherRepository()

    fun getCurrentWeather(lat :Double , lon:Double , apikey:String , unit:String  ) = viewModelScope.launch {
       val response = repo.getCurrentWeather(lat, lon , apikey,unit )
        if (response.isSuccessful){
            weatherLiveData.postValue(response.body())
        }else {
            // log
        }
    }

}