package com.example.ryangallagher

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //This is just telling Hilt that this is where our application starts.
class WeatherApplication: Application()