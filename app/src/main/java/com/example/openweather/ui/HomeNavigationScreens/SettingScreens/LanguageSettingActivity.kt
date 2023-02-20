package com.example.openweather.ui.HomeNavigationScreens.SettingScreens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.openweather.MainActivity
import com.example.openweather.R
import com.example.openweather.databinding.ActivityLanguageBinding
import com.example.openweather.databinding.ActivityModeBinding
import com.example.openweather.utils.Constants
import java.util.*

class LanguageSettingActivity : AppCompatActivity() {

    lateinit var binding : ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences: SharedPreferences =  getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val currentLanguage = sharedPreferences.getString(Constants.LANGUAGE , "en" )


        when(currentLanguage){
            "en" -> binding.radioButtonEn.isChecked=true
            "ar" -> binding.radioButtonAr.isChecked=true
        }

        binding.radioButtonEn.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                editor.putString(Constants.LANGUAGE, "en")
                editor.apply()
                setLocal(this , "en")
                refreshAppSetting()
            }
        }
        binding.radioButtonAr.setOnCheckedChangeListener { p0, p1 ->
            if (p1) {
                editor.putString(Constants.LANGUAGE, "ar")
                editor.apply()
                setLocal(this , "ar")
                refreshAppSetting()
            }
        }


    }
    fun setLocal(activity: Activity, langCode: String?) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val resources = activity.resources
        val config = resources.configuration

        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)


    }

     fun  refreshAppSetting(){
         val refresh = Intent(this, MainActivity::class.java)
         startActivity(refresh)
         this.finish()
     }

    }



