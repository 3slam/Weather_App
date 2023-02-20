package com.example.openweather.ui.HomeNavigationScreens.SettingScreens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.openweather.utils.Constants
import com.example.openweather.databinding.ActivityModeBinding

class ModeSettingActivity : AppCompatActivity() {
    lateinit var binding : ActivityModeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences: SharedPreferences =  getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentMode = AppCompatDelegate.getDefaultNightMode()

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
           binding.radioButtonDark.isChecked = true
            editor.putString(Constants.MODE,"Dark")
            editor.apply()
        } else {
            binding.radioButtonLight.isChecked = true
            editor.putString(Constants.MODE,"Light")
            editor.apply()
        }

        binding.radioButtonDark.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putString(Constants.MODE, "Dark")
                editor.apply()
            }
        }
        binding.radioButtonLight.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putString(Constants.MODE, "Light")
                editor.apply()
            }
        }


    }
}