package com.example.openweather.ui.HomeNavigationScreens.HomeScreens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.openweather.adapters.DaysWeatherAdapter
import com.example.openweather.adapters.HourlyWeatherAdapter
import com.example.openweather.data.models.CurrentWeatherResponse
import com.example.openweather.databinding.FragmentHomeBinding
import com.example.openweather.utils.Constants
import com.example.openweather.viewModels.CurrentWeatherViewModel
import com.example.openweather.viewModels.HourlyWeatherViewModel

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentWeatherViewModel : CurrentWeatherViewModel
    private lateinit var hourlyWeatherViewModel: HourlyWeatherViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val sharedPreferences: SharedPreferences = context?.getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)!!
        val lat = sharedPreferences.getString(Constants.LATATUIDE, "0.0" )?.toDouble()
        val lon = sharedPreferences.getString(Constants.LONGATUITE,"0.0")?.toDouble()
        val unit = sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT,"metric")


        currentWeatherViewModel = ViewModelProvider(this)[CurrentWeatherViewModel::class.java]
        currentWeatherViewModel.getCurrentWeather( lat!! , lon!!, Constants.API_KEY , unit!!)
        currentWeatherViewModel.weatherLiveData.observe(viewLifecycleOwner) {handlingCurrentWeatherResponse(it , unit)}

        hourlyWeatherViewModel = ViewModelProvider(this)[HourlyWeatherViewModel::class.java]
        hourlyWeatherViewModel.getCurrentWeatherHourly( lat!! , lon!!, Constants.API_KEY , unit!!)
        hourlyWeatherViewModel.weatherLiveData.observe(viewLifecycleOwner){
            val hourlyWeatherAdapter = HourlyWeatherAdapter(unit)
            binding.todayRv.apply {
                adapter = hourlyWeatherAdapter
            }
            hourlyWeatherAdapter.differ.submitList(it.list)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handlingCurrentWeatherResponse( currentWeatherResponse: CurrentWeatherResponse , unit:String){

        var tempUnit :String = when (unit) {
            "stander" -> " K"
            "metric" -> " °C"
            else -> " F"
        }

        var windSpeedUnit :String = when (unit) {
            "stander" -> " m/s"
            "metric" -> " m/s"
            else -> " miles/h"
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
        Glide.with(requireContext()).load(iconLink).into( binding.iconImage)
    }

}