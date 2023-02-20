package com.example.openweather.ui.onBoardingScreens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.openweather.R
import com.example.openweather.databinding.FragmentLoadingBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*


class LoadingFragment : Fragment() {

    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)

        binding.start.setOnClickListener {
            goToNextScreen()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goToNextScreen(){
        findNavController().navigate(R.id.action_loadingFragment_to_firstSplashScreenFragment)
    }



}