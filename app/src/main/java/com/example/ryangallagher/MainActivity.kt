package com.example.ryangallagher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val apiKey = "e23be7dabdbe9983722033759ffb9fc3"

    private lateinit var api: Api
    private lateinit var cityName: TextView
    private lateinit var currentTemp: TextView
    private lateinit var conditionIcon: ImageView
    private lateinit var feelsLike: TextView
    private lateinit var highTemp: TextView
    private lateinit var lowTemp: TextView
    private lateinit var humidity: TextView
    private lateinit var pressure: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityName = findViewById(R.id.city_name)
        currentTemp = findViewById(R.id.temperature)
        conditionIcon = findViewById(R.id.condition_icon)
        feelsLike = findViewById(R.id.feels_like)
        highTemp = findViewById(R.id.high)
        lowTemp = findViewById(R.id.low)
        humidity = findViewById(R.id.humidity)
        pressure = findViewById(R.id.pressure)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        api = retrofit.create(Api::class.java)
    }

    override fun onResume() {
        super.onResume()
        /**
         *  val call: Call<CurrentConditions> = api.getCurrentConditions("55124")
         *      Creates a call object for us through out API.
         *  "call.enqueue"
         *      puts the call on the call queue, which is internally managed by retrofit. We don't have to worry about it.
         *   Then we pass the Callback object to it. Finally, we have to implement two functions: onResponse and onFailure.
         */
        val call: Call<CurrentConditions> = api.getCurrentConditions("55124")
        call.enqueue(object: Callback<CurrentConditions> {      //similar to creating an anonymous inner class in Java.
            override fun onResponse(
                call: Call<CurrentConditions>,
                response: Response<CurrentConditions>
            ) {
                val currentConditions = response.body()         // with our Response object, we can get access to the body() function which will do the deserialization for us.
                currentConditions?.let {
                    bindData(it)                                //explained 1:35mins in. It is a null-safe way to make this function call.
                }
            }

            override fun onFailure(call: Call<CurrentConditions>, t: Throwable) {

            }

        })
    }

    private fun bindData(currentConditions: CurrentConditions) {
        cityName.text = currentConditions.name
        currentTemp.text = getString(R.string.temperature, currentConditions.main.temp.toInt()) //the "d" in %1$d wants an integer, so we cast temp to an int with .toInt()
        feelsLike.text = getString(R.string.feels_like, currentConditions.main.feelsLike.toInt())
        highTemp.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
        lowTemp.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
        humidity.text = getString(R.string.humidity, currentConditions.main.humidity.toInt())
        pressure.text = getString(R.string.pressure, currentConditions.main.pressure.toInt())

        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(conditionIcon)
    }

    fun forecastActivity(view: android.view.View) {
        val intent = Intent(this, ForecastActivity::class.java)
        startActivity(intent)
    }

    /*
    within the private class, he's got:
    private lateinit var button: Button

    then within the onCreate() function, he's got:
    button.setOnClickListener {
        startActivity(Intent(this, ForecastActivity::class.java))
    }
    */
}