package com.example.weatherapp.remote.service

import com.example.weatherapp.data.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    private val API_KEY: String
        get() = "369da94e89034fedabe131634232005"

    @GET("forecast.json")
    fun getWeather(
        @Query("q") cityName: String? = "London",
        @Query("key") key: String = API_KEY,
        @Query("days") days: Int = 7
    ) : Call<Model>
}