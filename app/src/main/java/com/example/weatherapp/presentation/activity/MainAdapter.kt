package com.example.weatherapp.presentation.activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.weatherapp.data.Forecastday
import com.example.weatherapp.databinding.ItemWeatherBinding

class MainAdapter: Adapter<MainAdapter.MainViewHolder>() {

    private val list = ArrayList<Forecastday>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Forecastday>?){
        list?.let { this.list.addAll(it) }
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemWeatherBinding): ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(model: Forecastday) {
            Glide.with(binding.icon).load("https:${model.day.condition.icon}").into(binding.icon)
            binding.date.text = model.date
            binding.max.text = model.day.maxtemp_c.toString()
            binding.min.text = model.day.mintemp_c.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(
        ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}