package com.example.ryangallagher

data class CurrentConditions(
    val weather: List<WeatherCondition>,
    val main: Currents,
    val name: String,

)