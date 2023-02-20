package com.example.openweather.data.models

data class Day3HourResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<HourlyWeatherItem>,
    val message: Int
)