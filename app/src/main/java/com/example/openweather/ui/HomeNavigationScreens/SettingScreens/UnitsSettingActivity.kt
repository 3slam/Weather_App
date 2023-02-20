package com.example.openweather.ui.HomeNavigationScreens.SettingScreens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.openweather.utils.Constants
import com.example.openweather.databinding.ActivityUnitsBinding

class UnitsSettingActivity : AppCompatActivity() {

   private lateinit var binding : ActivityUnitsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnitsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences: SharedPreferences =  getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        when(sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT, "metric")){
            "metric" -> binding.radioButtonCelsius.isChecked = true
            "imperial" -> binding.radioButtonFahrenheit.isChecked = true
            "stander" -> binding.radioButtonKelvin.isChecked = true
        }

        binding.radioButtonCelsius.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                editor.putString(Constants.TEMP_DEGREE_UNIT, "metric")
                editor.apply()
            }
        }

        binding.radioButtonFahrenheit.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                editor.putString(Constants.TEMP_DEGREE_UNIT, "imperial")
                editor.apply()
            }
        }

        binding.radioButtonKelvin.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                editor.putString(Constants.TEMP_DEGREE_UNIT, "stander")
                editor.apply()
            }
        }


    }
}