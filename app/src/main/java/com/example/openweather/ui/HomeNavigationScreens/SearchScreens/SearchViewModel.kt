package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.data.local.WeatherDataBase
import com.example.openweather.data.models.CitySearch
import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.data.models.Day3HourResponse
import com.example.openweather.repo.SearchCityRepository
import com.example.openweather.repo.WeatherRepository
import com.example.openweather.utils.Constants
import kotlinx.coroutines.launch

class SearchViewModel(context: Context): ViewModel() {

    val dataBase = WeatherDataBase.getInstance(context)
    var weatherLiveData = MutableLiveData<CurrentWeatherResponse>()

    private val searchCityRepository = SearchCityRepository(dataBase)
    private  val weatherRepository=WeatherRepository()

    fun getAllSearches() = searchCityRepository.getAllSearches()



    suspend fun insertCitySearchItem(citySearch: CitySearch)   {
        searchCityRepository.insertCitySearchItem(citySearch)
    }

    suspend fun deletCitySearchItem(citySearch: CitySearch)  {
        searchCityRepository.deletCitySearchItem(citySearch)
    }

    suspend fun deletAllSearches()  {
        searchCityRepository.deletAllSearches()
    }

}