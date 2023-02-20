package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweather.adapters.CitySearchAdapter
import com.example.openweather.data.models.CitySearch
import com.example.openweather.databinding.FragmentSearchBinding
import com.example.openweather.utils.Constants
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {

    private var  _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        var cityList :List<CitySearch> = ArrayList()
        val citySearchAdapter = CitySearchAdapter(requireContext())
        val searchProviderFactory = SearchProviderFactory(requireContext())
        searchViewModel = ViewModelProvider(this,searchProviderFactory).get(SearchViewModel::class.java)

        searchViewModel.getAllSearches().observe(viewLifecycleOwner, Observer {
            cityList= it
            cityList.reversed()

            citySearchAdapter.differ.submitList(cityList.toList())
            binding.recyclerViewSearchCity.apply {
                adapter = citySearchAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        })
        //  citySearchAdapter.setOnItemClickListener(cityList)


        binding.searchButton.setOnClickListener {

            var searchCity =  binding.searchEditTxt.text.toString()
            if (searchCity.isEmpty()){
                binding.searchEditTxt.error = "This field can not be empty"
            }else{
               searchViewModel.viewModelScope.launch {
                   searchViewModel.insertCitySearchItem(CitySearch(searchCity))
               }

                val intent = Intent(requireContext(), ResuiltSearchingActivity::class.java)
                intent.putExtra(Constants.SERACH_CITY, searchCity)
                startActivity(intent)

            }

        }
        binding.clearAllTxt.setOnClickListener {
            searchViewModel.viewModelScope.launch {
                searchViewModel.deletAllSearches()
            }
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}