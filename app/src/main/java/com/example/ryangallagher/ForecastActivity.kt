package com.example.ryangallagher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

/**CHECKLIST
 * []get onBind up and running
 * []go through and delete garbage comments
 * []thoroughly document everything.
 */

class ForecastActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FA_onCreate", "Beginning of Forecast Activity onCreate method")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pro.openweathermap.org/data/2.5/forecast/") //change the URL, possibly. Double check it. https://pro.openweathermap.org/forecast/daily
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        Log.d("Post-Moshi/Retrofit", "Line prior to api creation")
        api = retrofit.create(Api::class.java)

        //conditionIcon = findViewById(R.id.condition_icon)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        /*
        val adapter = MyAdapter()
        recyclerView.adapter = adapter
        */ //had issues doing it this way ^ when passing adapterData to MyAdapter and assigning that to the val called adapter on line 42.


        //recyclerView.adapter = MyAdapter(adapterData.temp.day) // simplified version of lines 42+43

        val actionBar = supportActionBar

        if (actionBar != null) {             // actionBar!!.title = "Forecast" accomplishes the same thing.
            actionBar.title = "Forecast"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()

        val call: Call<Forecast> = api.getForecast("55124")
        call.enqueue(object: Callback<Forecast> {      //similar to creating an anonymous inner class in Java.
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {
                val forecast = response.body()         // with our Response object, we can get access to the body() function which will do the deserialization for us.
                forecast?.let {  //let function, built-in function in Kotlin.
                    bindData(it.list)
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun bindData(forecast: List<DayForecast>) {
        recyclerView.adapter = MyAdapter(forecast)
    }
}