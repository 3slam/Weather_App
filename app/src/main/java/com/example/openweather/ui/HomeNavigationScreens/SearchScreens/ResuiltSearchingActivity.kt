package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.openweather.databinding.ActivityResuiltSearchingBinding
import com.example.openweather.utils.Constants

class ResuiltSearchingActivity : AppCompatActivity() {

    lateinit var binding: ActivityResuiltSearchingBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResuiltSearchingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val cityName = intent.getStringExtra(Constants.SERACH_CITY)

//           searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        val sharedPreferences: SharedPreferences =
            getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)!!
        val unit = sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT, "metric")

        //  searchViewModel.getCurrentWeatherByCity(cityName!! , Constants.API_KEY , unit!!)
/*

        searchViewModel.weatherLiveData.observe(this , androidx.lifecycle.Observer {
            binding.windTxt.text = it.wind.speed.toString()
            binding.tempTxt.text = it.main.temp.toString()
            binding.humidityTxt.text = it.main.humidity.toString()
            binding.descriptionTxt.text = it.weather[0].description
            binding.tempMax.text = it.main.temp_max.toString()
            binding.tempMin.text = it.main.temp_min.toString()
            binding.cityTxt.text = it.name

            binding.feelsTxt.text = it.main.feels_like.toString().plus(" K")

            val iconLink = "https://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"
            Glide.with(this).load(iconLink).into(binding.iconImage)
        })
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
} */
    }
}