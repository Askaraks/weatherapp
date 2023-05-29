package com.example.weatherapp.presentation.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.Model
import com.example.weatherapp.remote.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: WeatherRepository
): ViewModel() {

    var liveData = MutableLiveData<Model>()

    fun getWeather(name: String) {
        liveData = repo.getWeather(name) as MutableLiveData<Model>
    }
}