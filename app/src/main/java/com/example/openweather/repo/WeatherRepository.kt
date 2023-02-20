package com.example.openweather.repo

import com.example.openweather.data.remote.WeatherRetrofitInstance

class WeatherRepository {

    suspend fun getCurrentWeather( lat:Double ,lon :Double , apikey :String , unit:String ) =
        WeatherRetrofitInstance.api.getCurrentWeather(lat,lon,apikey,unit)

    suspend fun getCurrentWeatherByCity( q :String , apikey :String ,  unit:String) =
        WeatherRetrofitInstance.api.getCurrentWeatherByCity(q,apikey,unit)

    suspend fun getCurrentWeatherDaysAndHourly( lat:Double ,lon :Double , apikey :String ,  unit:String) =
        WeatherRetrofitInstance.api.getCurrentWeatherDaysAndHourly(lat,lon,apikey,unit)



}