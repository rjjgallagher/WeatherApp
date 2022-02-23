package com.example.ryangallagher


data class DayForecast(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: ForecastTemp,
    val pressure: Float,
    val humidity: Float,
    val weather: List<Weather>,
)