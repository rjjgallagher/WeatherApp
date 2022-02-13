package com.example.ryangallagher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ForecastActivity : AppCompatActivity() {

    //his code. He put this in main. I think it goes here for me - in this activity.
    private lateinit var recyclerView: RecyclerView
    private lateinit var api: Api

    private lateinit var forecast: Forecast
    private lateinit var conditionIcon: ImageView


    private val forecastTemp1 = ForecastTemp(72f, 80f, 65f)
    private val forecastTemp2 = ForecastTemp(71f, 81f, 67f)
    private val forecastTemp3 = ForecastTemp(68f, 75f, 60f)
    private val forecastTemp4 = ForecastTemp(66f, 70f, 58f)
    private val forecastTemp5 = ForecastTemp(70f, 80f, 65f)
    private val forecastTemp6 = ForecastTemp(70f, 78f, 64f)
    private val forecastTemp7 = ForecastTemp(82f, 88f, 76f)
    private val forecastTemp8 = ForecastTemp(81f, 87f, 74f)
    private val forecastTemp9 = ForecastTemp(81f, 84f, 78f)
    private val forecastTemp10 = ForecastTemp(79f, 84f, 75f)
    private val forecastTemp11 = ForecastTemp(76f, 80f, 70f)
    private val forecastTemp12 = ForecastTemp(73f, 80f, 70f)
    private val forecastTemp13 = ForecastTemp(70f, 77f, 64f)
    private val forecastTemp14 = ForecastTemp(66f, 72f, 60f)
    private val forecastTemp15 = ForecastTemp(80f, 91f, 75f)
    private val forecastTemp16 = ForecastTemp(77f, 82f, 71f)

    /*
        DayForecast(1641079625, 1641303060, 1643756454, forecastTemp1, 1010F, 98F), // 1/1
        DayForecast(1641166071, 1641130320, 1643756514, forecastTemp2, 1011f, 50f), // 1/2
        DayForecast(1641252420, 1641216780, 1643756634, forecastTemp3, 1005f, 70f), // 1/3
        DayForecast(1641338822, 1641303240, 1643756694, forecastTemp4, 1020f, 100f), // 1/4
        DayForecast(1641398702, 1641560520, 1643756754, forecastTemp5, 1022f, 90f), // 1/5
        DayForecast(1641451382, 1641563040, 1643759094, forecastTemp6, 1033f, 29f), // 1/6
        DayForecast(1641567962, 1641584400, 1643759034, forecastTemp7, 1025f, 54f), // 1/7
        DayForecast(1641684422, 1641562680, 1643757774, forecastTemp8, 1023f, 58f), // 1/8
        DayForecast(1641771242, 1641562740, 1641749100, forecastTemp9, 1024f, 77f), // 1/9
        DayForecast(1641818102, 1641562800, 1643758374, forecastTemp10, 1013f, 80f), // 1/10
        DayForecast(1641912842, 1641562860, 1643757174, forecastTemp11, 1030f, 86f), // 1/11
        DayForecast(1642006442, 1641562920, 1643757114, forecastTemp12, 1023f, 82f), // 1/12
        DayForecast(1642107782, 1641562980, 1643756994, forecastTemp13, 1014f, 30f), // 1/13
        DayForecast(1642165382, 1641563040, 1643759694, forecastTemp14, 1028f, 24f), // 1/14
        DayForecast(1642242122, 1641563100, 1643758974, forecastTemp15, 1029f, 70f), // 1/15
        DayForecast(1642340642, 1641563160, 1643757174, forecastTemp16, 1025f, 72f),
     */

    //private val adapterData = MyAdapter(DayForecast(parameters,more params))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        conditionIcon = findViewById(R.id.condition_icon)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        api = retrofit.create(Api::class.java)

        recyclerView = findViewById(R.id.recyclerView)                  //his code
        recyclerView.layoutManager = LinearLayoutManager(this)   //his code

        /*
        val adapter = MyAdapter()
        recyclerView.adapter = adapter
        */ //had issues doing it this way ^ when passing adapterData to MyAdapter and assigning that to the val called adapter on line 42.

        recyclerView.adapter = MyAdapter() // simplified version of lines 42+43

        val actionBar = supportActionBar

        if (actionBar != null) {             // actionBar!!.title = "Forecast" accomplishes the same thing.
            actionBar.title = "Forecast"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()

        val call: Call<Forecast> = api.getForecast("55124")
        call.enqueue(object: Callback<Forecast> {
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {
                val forecast = response.body()
                forecast?.let {
                    bindData(it)
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {

            }

        })
    }

    private fun bindData(forecast: Forecast) {
        val iconName = forecast.temp.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(conditionIcon)
    }
}