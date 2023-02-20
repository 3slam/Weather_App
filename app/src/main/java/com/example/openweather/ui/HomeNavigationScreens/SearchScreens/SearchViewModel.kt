package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweather.data.local.WeatherDataBase
import com.example.openweather.data.models.CitySearch
import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.repo.SearchCityRepository
import com.example.openweather.repo.WeatherRepository
import kotlinx.coroutines.launch

class SearchViewModel(context: Context): ViewModel() {

    val dataBase = WeatherDataBase.getInstance(context)
    private val repo = SearchCityRepository(dataBase)
    fun getAllSearches() = repo.getAllSearches()


    suspend fun insertCitySearchItem(citySearch: CitySearch)   {
        repo.insertCitySearchItem(citySearch)
    }

    suspend fun deletCitySearchItem(citySearch: CitySearch)  {
        repo.deletCitySearchItem(citySearch)
    }

    suspend fun deletAllSearches()  {
        repo.deletAllSearches()
    }

}