package com.example.openweather.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "city_table")
class CitySearch (val city:String) {
    @PrimaryKey(autoGenerate = true) var city_id : Int = 0
}