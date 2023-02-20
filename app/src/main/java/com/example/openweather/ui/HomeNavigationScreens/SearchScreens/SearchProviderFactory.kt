package com.example.openweather.ui.HomeNavigationScreens.SearchScreens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SearchProviderFactory(var context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(context) as T
        }
        throw IllegalArgumentException("view model class not found")
    }
}

