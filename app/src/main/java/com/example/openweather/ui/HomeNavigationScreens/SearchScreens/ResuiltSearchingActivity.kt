package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.databinding.ActivityResuiltSearchingBinding
import com.example.openweather.utils.Constants
import com.example.openweather.viewModels.CurrentWeatherByCityViewModel
import java.text.SimpleDateFormat
import java.util.*

class ResuiltSearchingActivity : AppCompatActivity() {

    lateinit var binding: ActivityResuiltSearchingBinding
    private lateinit var currentWeatherByCityViewModel: CurrentWeatherByCityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResuiltSearchingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val cityName = intent.getStringExtra(Constants.SERACH_CITY)

        currentWeatherByCityViewModel = ViewModelProvider(this)[CurrentWeatherByCityViewModel::class.java]

        val sharedPreferences: SharedPreferences =
            getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)!!
        val unit = sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT, "metric")
        val language = sharedPreferences.getString(Constants.LANGUAGE ,"en")

        currentWeatherByCityViewModel.getWeatherByCity(cityName!! , Constants.API_KEY , unit!! )


        currentWeatherByCityViewModel.weatherLiveData.observe(this) {
            handlingCurrentWeatherResponse(it, unit )
        }
    }

    private fun handlingCurrentWeatherResponse(
        currentWeatherResponse: CurrentWeatherResponse,
        unit: String,

    ){

        var tempUnit  =""
        var windSpeedUnit =""

            tempUnit  = when(unit){

                "stander" -> " K"
                "metric" -> " °C"
                else -> " F"
            }

            windSpeedUnit = when(unit) {

                "stander" -> " m/s"
                "metric" -> " m/s"
                else -> "miles/h"

            }


        val cityTxtFormat = SimpleDateFormat("dd MMM yyyy")
        val cityTxtData =  Date(currentWeatherResponse.sys.sunset * 1000)
        binding.dataTxt.text=  cityTxtFormat.format(cityTxtData)

        val sunsetTxtTxtFormat = SimpleDateFormat("hh : mm  aa")
        val sunsetTxtData =  Date(currentWeatherResponse.sys.sunset * 1000)
        binding.sunsetTxt.text  =  sunsetTxtTxtFormat.format(sunsetTxtData)

        val sunriseTxtTxtFormat = SimpleDateFormat("hh : mm  aa")
        val sunriseTxtData =  Date(currentWeatherResponse.sys.sunrise * 1000)
        binding.sunriseTxt.text =  sunriseTxtTxtFormat.format(sunriseTxtData)

        binding.windTxt.text = currentWeatherResponse.wind.speed.toString().plus(windSpeedUnit)
        binding.tempTxt.text = currentWeatherResponse.main.temp.toString().plus(tempUnit)
        binding.humidityTxt.text = currentWeatherResponse.main.humidity.toString().plus(" %")
        binding.descriptionTxt.text = currentWeatherResponse.weather[0].description
        binding.pressureTxt.text= currentWeatherResponse.main.pressure.toString().plus(" hPa")
        binding.cityTxt.text = currentWeatherResponse.name

        val iconLink =  "https://openweathermap.org/img/wn/${currentWeatherResponse.weather[0].icon}@2x.png"
        Glide.with(this).load(iconLink).into( binding.iconImage)
    }

    fun String.toDate(dateFormat: String = "yyyy-MM-dd", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(this)
    }

    fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
}
