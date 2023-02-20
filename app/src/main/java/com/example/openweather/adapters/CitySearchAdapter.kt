package com.example.openweather.adapters

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.openweather.data.models.CitySearch
import com.example.openweather.databinding.CustmItemSearchBinding

class CitySearchAdapter (context: Context ): RecyclerView.Adapter<CitySearchAdapter.CitySearchViewHolder>() {
    val context= context

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    private lateinit var clickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    inner class CitySearchViewHolder (val binding : CustmItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CitySearch>() {
        override fun areItemsTheSame(oldItem: CitySearch, newItem: CitySearch): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CitySearch, newItem: CitySearch): Boolean {
            return  oldItem.city_id == newItem.city_id
        }
    }


    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):     CitySearchViewHolder {
        val binding =  CustmItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  CitySearchViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CitySearchViewHolder, position: Int) {
        val item = differ.currentList[position]
          holder.binding.citySearchTxt.text= item.city

     }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}