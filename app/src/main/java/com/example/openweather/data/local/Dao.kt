package com.example.openweather.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.openweather.data.models.CitySearch


@Dao
interface Dao {

    @Insert
    suspend fun insertCitySearchItem(citySearch :CitySearch)

    @Delete
    suspend fun deletCitySearchItem(citySearch: CitySearch)

    @Query("SELECT * FROM city_table")
    fun getAllSearches() : LiveData<List<CitySearch>>

    @Query("DELETE FROM city_table")
    suspend fun deletAllSearches()

}