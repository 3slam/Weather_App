package com.example.openweather.ui.HomeNavigationScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.openweather.R
import com.example.openweather.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_container_for_home_screen)
        binding.bottomNavigationForHomeScreen.setupWithNavController(navController)


    }

    }
