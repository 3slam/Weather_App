package com.example.openweather.repo

import androidx.lifecycle.LiveData
import com.example.openweather.data.local.Dao
import com.example.openweather.data.local.WeatherDataBase
import com.example.openweather.data.models.CitySearch

class SearchCityRepository(private var dataBase: WeatherDataBase) {

    suspend fun insertCitySearchItem(citySearch: CitySearch){
        dataBase.getWeatherDao().insertCitySearchItem(citySearch)
    }

    suspend fun deletCitySearchItem(citySearch: CitySearch){
        dataBase.getWeatherDao().deletCitySearchItem(citySearch)
    }

    fun getAllSearches(): LiveData<List<CitySearch>> {
        return dataBase.getWeatherDao().getAllSearches()
    }


    suspend fun deletAllSearches(){
        dataBase.getWeatherDao().deletAllSearches()
    }

}