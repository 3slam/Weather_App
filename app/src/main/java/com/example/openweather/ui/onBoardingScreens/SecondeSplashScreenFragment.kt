package com.example.openweather.ui.onBoardingScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.openweather.R
import com.example.openweather.databinding.FragmentSecondeSplashScreenBinding


class SecondeSplashScreenFragment : Fragment() {

    private var _binding: FragmentSecondeSplashScreenBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSecondeSplashScreenBinding.inflate(inflater, container, false)

        binding.nextTxt.setOnClickListener { goToNextScreen() }

        binding.skipTxt.setOnClickListener{ goToHomeScreen() }

        binding.backTxt.setOnClickListener { goToBackScreen() }
        binding.progressBar.max = 100
        binding.progressBar.progress = 74
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToNextScreen(){
        findNavController().navigate(R.id.action_secondeSplashScreenFragment_to_thirdSplashScreenFragment)
    }
    private fun goToBackScreen(){
        findNavController().navigate(R.id.action_secondeSplashScreenFragment_to_firstSplashScreenFragment)
    }
    private fun goToHomeScreen(){
        findNavController().navigate(R.id.action_secondeSplashScreenFragment_to_homeActivity2)
    }

}