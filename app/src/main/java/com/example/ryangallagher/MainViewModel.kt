package com.example.ryangallagher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val service: Api): ViewModel() {
    val currentConditions: MutableLiveData<CurrentConditions> = MutableLiveData()

    fun loadData() {
        val call = service.getCurrentConditions("55124")
        call.enqueue(object :
            Callback<CurrentConditions> {      //similar to creating an anonymous inner class in Java.
            override fun onResponse(
                call: Call<CurrentConditions>,
                response: Response<CurrentConditions>
            ) {
                // with our Response object, we can get access to the body() function which will do the deserialization for us.
                response.body()?.let {
                    currentConditions.value = it
                }
            }
//          ) {
//            val currentConditions = response.body()         // with our Response object, we can get access to the body() function which will do the deserialization for us.
//            currentConditions?.let {
//                currentConditions.value = it
//            }
//        }

            override fun onFailure(call: Call<CurrentConditions>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}