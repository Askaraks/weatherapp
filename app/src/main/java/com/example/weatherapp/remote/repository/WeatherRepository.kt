package com.example.weatherapp.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.Model
import com.example.weatherapp.remote.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: ApiService
){

    fun getWeather(name: String): LiveData<Model> {
        val liveData = MutableLiveData<Model>()
        api.getWeather(cityName = name).enqueue(object: Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                liveData.value = response.body()
            }
            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return liveData
    }
}