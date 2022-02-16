package com.example.ryangallagher

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MyAdapter(private val dayForecastData: List<DayForecast>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    @SuppressLint("NewApi")
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val timeFormatter = DateTimeFormatter.ofPattern("h:mma")
        private val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
        private val dateView: TextView = view.findViewById(R.id.date)

        private val sunriseTimeView: TextView = view.findViewById(R.id.sunrise_time)
        private val sunsetTimeView: TextView = view.findViewById(R.id.sunset_time)

        private val highTimeView: TextView = view.findViewById(R.id.high_temp)
        private val lowTimeView: TextView = view.findViewById(R.id.low_temp)
        private val currentTimeView: TextView = view.findViewById(R.id.curr_temp)


        @SuppressLint("ResourceType")
        fun bind(data: DayForecast) {
            highTimeView.text = "High: ${data.temp.max.toInt()}°"
            lowTimeView.text = "Low: ${data.temp.min.toInt()}°"
            currentTimeView.text = "Temp: ${data.temp.day.toInt()}°"


            val instant = Instant.ofEpochSecond(data.date)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            dateView.text = dateFormatter.format(dateTime)

            val instant2 = Instant.ofEpochSecond(data.sunrise)
            val sunriseTime = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault())
            sunriseTimeView.text = "Sunrise: ${timeFormatter.format(sunriseTime)}"
            //sunriseTimeView.text = timeFormatter.format(sunriseTime)
            //sunriseTimeView.text = "Sunrise: ${timeFormatter.format(sunriseTime)}"


            val instant3 = Instant.ofEpochSecond(data.sunset)
            val sunsetTime = LocalDateTime.ofInstant(instant3, ZoneId.systemDefault())
            sunsetTimeView.text = "Sunrise: ${timeFormatter.format(sunsetTime)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_date, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dayForecastData[position])
    }

    override fun getItemCount() = dayForecastData.size
}