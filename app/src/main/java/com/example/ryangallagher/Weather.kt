package com.example.ryangallagher

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Weather(
    val icon: String
) : Parcelable