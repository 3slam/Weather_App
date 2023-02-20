package com.example.openweather.adapters

 import android.view.LayoutInflater
 import android.view.ViewGroup
 import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
 import com.example.openweather.data.models.HourlyWeatherItem
 import com.example.openweather.databinding.CustmItemHourlyWeatherBinding

 import com.squareup.picasso.Picasso
 import java.text.SimpleDateFormat
 import java.util.*

class HourlyWeatherAdapter (unit :String) : RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {
      private val tempUnit = unit
      inner class HourlyWeatherViewHolder (val binding : CustmItemHourlyWeatherBinding) : RecyclerView.ViewHolder(binding.root)  {

    }

    private val differCallback = object : DiffUtil.ItemCallback<HourlyWeatherItem>() {
        override fun areItemsTheSame(oldItem: HourlyWeatherItem, newItem: HourlyWeatherItem): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: HourlyWeatherItem, newItem: HourlyWeatherItem): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {

        val binding =  CustmItemHourlyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyWeatherViewHolder(binding)
    }


    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
          val hourlyWeatherItem = differ.currentList[position]
          val iconLink =  "https://openweathermap.org/img/wn/${hourlyWeatherItem.weather[0].icon}@2x.png"
          Picasso.get().load(iconLink).into(holder.binding.weatherImage)

        val cityTxtFormat = SimpleDateFormat("hh : mm  aa")
        val cityTxtData =  Date(hourlyWeatherItem.dt.toLong() * 1000)
        holder.binding.dataTxt.text =  cityTxtFormat.format(cityTxtData)


        var unit :String = when (tempUnit) {
            "stander" -> " K"
            "metric" -> " °C"
            else -> " F"
        }
         holder.binding.tempTxt.text = hourlyWeatherItem.main.temp.toString().plus(unit)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}