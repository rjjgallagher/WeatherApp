package com.example.ryangallagher

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayForecast(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: ForecastTemp,
    val pressure: Float,
    val humidity: Float,
    val weather: List<Weather>,
) : Parcelable