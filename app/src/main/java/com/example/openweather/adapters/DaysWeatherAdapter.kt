package com.example.openweather.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.data.models.HourlyWeatherItem
import com.example.openweather.databinding.CustmItemDaysWeatherBinding
import com.example.openweather.utils.Constants
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
class DaysWeatherAdapter (private val context: Context  ): RecyclerView.Adapter<DaysWeatherAdapter.DaysWeatherViewHolder>() {
    var thisContext = context

    inner class DaysWeatherViewHolder (val binding : CustmItemDaysWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HourlyWeatherItem>() {
        override fun areItemsTheSame(oldItem: HourlyWeatherItem, newItem: HourlyWeatherItem): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: HourlyWeatherItem, newItem: HourlyWeatherItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysWeatherViewHolder {
        val binding =  CustmItemDaysWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysWeatherViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DaysWeatherViewHolder, position: Int) {
       val daysWeatherItem = differ.currentList[position]

        val sharedPreferences: SharedPreferences = context?.getSharedPreferences(Constants.SH_PER_FILE_NAME, Context.MODE_PRIVATE)!!

        val unit = sharedPreferences.getString(Constants.TEMP_DEGREE_UNIT,"metric")
        var tempUnit : String
        when (unit) {
            "stander" ->  tempUnit =" K"
            "metric" ->   tempUnit = " °C"
            else ->       tempUnit = " F"
        }

         val iconLink =  "https://openweathermap.org/img/wn/${daysWeatherItem.weather[0].icon}@2x.png"
         Picasso.get().load(iconLink).into(holder.binding.imageIcon)

        val dayTxtFormat = SimpleDateFormat("dd MMM yyyy")
        val dayTxtData =  Date(daysWeatherItem.dt.toLong() * 1000)
        holder.binding.dayTxt.text=  dayTxtFormat.format(dayTxtData)

        val timeTxtFormat = SimpleDateFormat("hh : mm  aa")
        val timeTxtData =  Date(daysWeatherItem.dt.toLong() * 1000)
        holder.binding.timeTxt.text=  timeTxtFormat.format(timeTxtData)

        holder.binding.tempTxt.text = daysWeatherItem.main.temp.toString().plus(tempUnit)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}