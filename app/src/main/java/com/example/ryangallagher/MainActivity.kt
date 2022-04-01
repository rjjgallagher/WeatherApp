package com.example.ryangallagher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ryangallagher.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*
 * Now we have our MainActivity operating as a view and just a view. Only has view logic in it.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //---
    private lateinit var binding: ActivityMainBinding
    //-

//    @Inject
//    lateinit var viewModel: MainViewModel // you cannot inject private fields. viewModel must be public.


    override fun onCreate(savedInstanceState: Bundle?) {
        //---
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //-

//        binding.forecastButton.setOnClickListener {
//            startActivity(Intent(this, ForecastActivity::class.java))
//        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.currentConditions.observe(this) { currentConditions ->
//            bindData(currentConditions)
//        }
//        viewModel.loadData()
//    }

//    private fun bindData(currentConditions: CurrentConditions) {
//        binding.cityName.text = currentConditions.name
//        binding.temperature.text = getString(
//            R.string.temperature,
//            currentConditions.main.temp.toInt()
//        ) //the "d" in %1$d wants an integer, so we cast temp to an int with .toInt()
//        binding.feelsLike.text =
//            getString(R.string.feels_like, currentConditions.main.feelsLike.toInt())
//        binding.high.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
//        binding.low.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
//        binding.humidity.text =
//            getString(R.string.humidity, currentConditions.main.humidity.toInt())
//        binding.pressure.text =
//            getString(R.string.pressure, currentConditions.main.pressure.toInt())
//
//        val iconName = currentConditions.weather.firstOrNull()?.icon
//        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
//        Glide.with(this)
//            .load(iconUrl)
//            .into(binding.conditionIcon)
//    }
}