package com.example.ryangallagher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun forecastActivity(view: android.view.View) {
        val intent = Intent(this, ForecastActivity::class.java)
        startActivity(intent)
    }
    //githubtest1234
    /*
    within the private class, he's got:
    private lateinit var button: Button

    then within the onCreate() function, he's got:
    button.setOnClickListener {
        startActivity(Intent(this, ForecastActivity::class.java))
    }
     */

}