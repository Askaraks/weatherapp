package com.example.weatherapp.presentation.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.dialog.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DialogFragment.Result {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel : MainViewModel
    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getWeather("London")
        initWeahter()
        initButton()
    }

    private fun initButton() {
        binding.locationBtn.setOnClickListener {
            DialogFragment(this).show(this.supportFragmentManager, "")
        }
    }

    @SuppressLint("CheckResult")
    private fun initWeahter() {
        viewModel.liveData.observe(this) {
            binding.txtTemp.text = it.current.temp_c.toString()
            binding.locationBtn.text = it.location.name
            binding.txtSunny.text = it.current.condition.text
            Glide.with(this).load("https:${it.current.condition.icon}").into(binding.imgSunny)
            binding.xtemp.text = it.forecast.forecastday[0].day.maxtemp_c.toString()
            binding.stemp.text = it.forecast.forecastday[0].day.mintemp_c.toString()
            binding.percent.text = it.current.humidity.toString()
            binding.icPressure.text = it.current.pressure_mb.toString()
            binding.speed.text = it.current.wind_kph.toString()
            binding.time1.text = it.forecast.forecastday[0].astro.sunset
            binding.time.text = it.forecast.forecastday[0].astro.sunrise
            binding.time2.text = formatUnixTimestamp(it.location.localtime_epoch.toLong(), it.location.tz_id)

            val list = it.forecast.forecastday
            adapter.setList(list)
            binding.tvWeather.adapter = adapter
        }
    }


    fun formatUnixTimestamp(unixTimestamp: Long, zoneId: String): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zonedDateTime = instant.atZone(ZoneId.of(zoneId))
        val formatter = DateTimeFormatter.ofPattern("HH'h' mm'm'", Locale.ENGLISH)
        return formatter.format(zonedDateTime)
    }

    override fun click(name: String) {
        viewModel.getWeather(name)
        initWeahter()
    }
}