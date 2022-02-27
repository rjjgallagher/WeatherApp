package com.example.ryangallagher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.ryangallagher.databinding.ActivityForecastBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val service: Api): ViewModel() {
    val forecast: MutableLiveData<Forecast> = MutableLiveData()

    fun loadData() {
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://pro.openweathermap.org/data/2.5/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//
//        api = retrofit.create(Api::class.java)
        val call: Call<Forecast> = service.getForecast("55124")
        call.enqueue(object: Callback<Forecast> { //similar to creating an anonymous inner class in Java.
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {                         // with our Response object, we can get access to the body() function which will do the deserialization for us.
                response.body()?.let {  // let function, built-in function in Kotlin.
                    forecast.value = it
                }
            }
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

