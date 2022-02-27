package com.example.ryangallagher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ryangallagher.databinding.ActivityForecastBinding
import com.example.ryangallagher.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/* CHECKLIST
 * []go through and delete garbage comments
 * []thoroughly document everything.
 */

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    @Inject lateinit var viewModel: ForecastViewModel
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://pro.openweathermap.org/data/2.5/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//
//        api = retrofit.create(Api::class.java)

        val actionBar = supportActionBar

        if (actionBar != null) {             // actionBar!!.title = "Forecast" accomplishes the same thing.
            actionBar.title = "Forecast"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.forecast.observe(this) {forecast ->
            binding.recyclerView.adapter = MyAdapter(forecast.list)
        }
        viewModel.loadData()

        /*
//        val call: Call<Forecast> = api.getForecast("55124")
//        call.enqueue(object: Callback<Forecast> {      //similar to creating an anonymous inner class in Java.
//            override fun onResponse(
//                call: Call<Forecast>,
//                response: Response<Forecast>
//            ) {
//                val forecast = response.body() // with our Response object, we can get access to the body() function which will do the deserialization for us.
//                forecast?.let {  //let function, built-in function in Kotlin.
//                    bindData(it.list)
//                }
//            }
//            override fun onFailure(call: Call<Forecast>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })

         */
    }
}