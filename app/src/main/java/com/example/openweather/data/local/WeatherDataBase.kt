package com.example.openweather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.openweather.data.models.CitySearch

@Database(entities = [CitySearch::class], version = 1)

abstract class WeatherDataBase : RoomDatabase() {

    abstract fun getWeatherDao() : Dao

    companion object {
        @Volatile
        private var instance: WeatherDataBase? = null

        fun getInstance(context: Context) : WeatherDataBase {
            return instance ?: synchronized(Any()){
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WeatherDataBase::class.java,
            "weatherdatabase").build()
    }
}