package com.example.openweather.data.remote

import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.data.models.Day3HourResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
       @Query("lat") lat:Double ,
       @Query("lon") lon:Double ,
       @Query("appid") appid:String ,
        @Query("units") unit:String ,
    ) : Response<CurrentWeatherResponse>

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") q:String ,
        @Query("appid") appid:String ,
        @Query("units") unit:String
    ) : Response<CurrentWeatherResponse>


    @GET("data/2.5/forecast")
    suspend fun getCurrentWeatherDaysAndHourly(
        @Query("lat") lat:Double ,
        @Query("lon") lon:Double ,
        @Query("appid") appid:String ,
        @Query("units") unit:String ,
    ) : Response<Day3HourResponse>



}