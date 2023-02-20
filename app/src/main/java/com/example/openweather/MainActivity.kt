package com.example.openweather

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.openweather.databinding.ActivityMainBinding
import com.example.openweather.ui.HomeNavigationScreens.SettingScreens.LanguageSettingActivity
import com.example.openweather.utils.Constants
import com.example.openweather.utils.Constants.Companion.LANGUAGE
import com.google.android.gms.location.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getCurrentLocation()

        initializeAppTheme()

        initializeAppLanguage()

    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(){
        if (isLocationPermissionTaken()){
            if (isLocationEnable()){


                var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                lateinit var locationCallback: LocationCallback

                var locationRequest: LocationRequest =
                    LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY , TimeUnit.MINUTES.toMillis(5)).apply {
                        setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                        setDurationMillis(TimeUnit.MINUTES.toMillis(5))
                        setWaitForAccurateLocation(true)
                        setMaxUpdates(1)
                    }.build()

                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)
                        val currentLocation: Location? = p0.lastLocation
                        currentLocation?.latitude

                        currentLocation?.time
                        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.SH_PER_FILE_NAME,Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor =  sharedPreferences.edit()

                        editor.putString(Constants.LATATUIDE, currentLocation?.latitude.toString())
                        editor.putString(Constants.LONGATUITE,  currentLocation?.longitude.toString())
                        editor.apply()

                    }
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

            }else{
                requestLocationEnable()
            }
        }else {
            requestLocationPermission()
            getCurrentLocation()
        }
    }


    private fun isLocationPermissionTaken(): Boolean {
        if (ActivityCompat.checkSelfPermission(this ,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED )
            if (ActivityCompat.checkSelfPermission(this ,
                    ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
                return true
        return false
    }


    private fun isLocationEnable(): Boolean {
        val locationManager : LocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }



    private fun requestLocationEnable() {
        val gpsOptionsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(gpsOptionsIntent)
    }


    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
            ), Constants.REQUEST_PERMISSION_CODE )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ( requestCode == Constants.REQUEST_PERMISSION_CODE &&
            permissions.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation()
        }
        else {
            requestLocationPermission()
        }

    }
    private fun initializeAppTheme(){

        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.SH_PER_FILE_NAME,Context.MODE_PRIVATE)
        val mode = sharedPreferences.getString(Constants.MODE,"Dark")

        if (mode=="Dark"){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }



    private fun initializeAppLanguage() {

        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.SH_PER_FILE_NAME,Context.MODE_PRIVATE)
        val language = sharedPreferences.getString(Constants.LANGUAGE,"en")
        val languageSettingActivity = LanguageSettingActivity()
        languageSettingActivity.setLocal(this , language)

    }



}