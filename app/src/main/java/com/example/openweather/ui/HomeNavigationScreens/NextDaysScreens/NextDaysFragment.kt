package com.example.openweather.ui.HomeNavigationScreens.NextDaysScreens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.openweather.R
import com.example.openweather.adapters.DaysWeatherAdapter
import com.example.openweather.adapters.HourlyWeatherAdapter
import com.example.openweather.databinding.FragmentFavouriterBinding
import com.example.openweather.databinding.FragmentHomeBinding
import com.example.openweather.utils.Constants
import com.example.openweather.viewModels.CurrentWeatherViewModel
import com.example.openweather.viewModels.HourlyWeatherViewModel


class NextDaysFragment : Fragment() {

    private var _binding: FragmentFavouriterBinding? = null
    private val binding get() = _binding!!

    private lateinit var hourlyWeatherViewModel: HourlyWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriterBinding.inflate(inflater, container, false)

        val sharedPreferences: SharedPreferences = context?.getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)!!
        val lat = sharedPreferences.getString(Constants.LATATUIDE, "0.0" )?.toDouble()
        val lon = sharedPreferences.getString(Constants.LONGATUITE,"0.0")?.toDouble()
        val unit = sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT,"metric")


        hourlyWeatherViewModel = ViewModelProvider(this)[HourlyWeatherViewModel::class.java]
        hourlyWeatherViewModel.getCurrentWeatherHourly( lat!! , lon!!, Constants.API_KEY , unit!!)

        hourlyWeatherViewModel.weatherLiveData.observe(viewLifecycleOwner){
            val daysWeatherAdapter = DaysWeatherAdapter(requireContext())
            binding.daysRv.apply {
                adapter = daysWeatherAdapter
            }
            daysWeatherAdapter.differ.submitList(it.list)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}