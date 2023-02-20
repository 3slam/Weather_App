package com.example.openweather.ui.HomeNavigationScreens.SettingScreens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.openweather.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

         binding.langTxt.setOnClickListener {
         goToLanguageActivity()
         }

        binding.modeTxt.setOnClickListener {
         goToModeActivity()
        }

        binding.unitsTxt.setOnClickListener {
        goToUnitsActivity()
        }

        return binding.root
    }

    private fun goToLanguageActivity(){
        val intent = Intent(requireContext(), LanguageSettingActivity::class.java)
        startActivity(intent)
    }
    private fun goToModeActivity(){
        val intent = Intent(requireContext(), ModeSettingActivity::class.java)
        startActivity(intent)
    }
    private fun goToUnitsActivity(){
        val intent = Intent(requireContext(), UnitsSettingActivity::class.java)
        startActivity(intent)
    }
}