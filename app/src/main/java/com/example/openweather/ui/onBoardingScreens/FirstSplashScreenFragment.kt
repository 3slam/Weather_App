package com.example.openweather.ui.onBoardingScreens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.openweather.R
import com.example.openweather.databinding.FragmentFirstSplashScreenBinding
import com.example.openweather.utils.Constants


class FirstSplashScreenFragment : Fragment() {

    private var _binding: FragmentFirstSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFirstSplashScreenBinding.inflate(inflater, container, false)

        val sharedPreferences: SharedPreferences =  requireContext().getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.openOnce,"t")
        editor.apply()
        binding.nextTxt.setOnClickListener { goToNextScreen() }

        binding.skipTxt.setOnClickListener{ goToHomeScreen() }
        binding.backTxt.setOnClickListener { goToBackScreen() }
        binding.progressBar.max = 100
        binding.progressBar.progress = 30

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToNextScreen(){
        findNavController().navigate(R.id.action_firstSplashScreenFragment_to_secondeSplashScreenFragment)
    }
    private fun goToBackScreen(){
        findNavController().navigate(R.id.action_firstSplashScreenFragment_to_loadingFragment)
    }
    private fun goToHomeScreen(){
        findNavController().navigate(R.id.action_firstSplashScreenFragment_to_homeActivity2)
    }
}