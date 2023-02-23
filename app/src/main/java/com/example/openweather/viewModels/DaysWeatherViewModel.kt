package com.example.openweather.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.data.models.Day3HourResponse
import com.example.openweather.repo.WeatherRepository
import kotlinx.coroutines.launch

class DaysWeatherViewModel :ViewModel() {

    var weatherLiveData = MutableLiveData<Day3HourResponse>()
    private val repo = WeatherRepository()

    fun getCurrentWeatherDaysAndHourly(lat :Double , lon:Double , apikey:String , unit:String  ) = viewModelScope.launch {
        val response = repo.getCurrentWeatherDaysAndHourly(lat, lon , apikey,unit )
        weatherLiveData.postValue(response.body())
    }


}