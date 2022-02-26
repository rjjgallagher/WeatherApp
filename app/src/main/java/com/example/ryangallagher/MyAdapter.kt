package com.example.ryangallagher

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ryangallagher.databinding.ActivityForecastBinding
import com.example.ryangallagher.databinding.RowDateBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MyAdapter(private val dayForecastData: List<DayForecast>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    @SuppressLint("NewApi")
    //formally: class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class ViewHolder(private val itemBinding: RowDateBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val timeFormatter = DateTimeFormatter.ofPattern("h:mma")
        private val dateFormatter = DateTimeFormatter.ofPattern("MMM d")

        //private val dateView: TextView = view.findViewById(R.id.date)

        //private val sunriseTimeView: TextView = view.findViewById(R.id.sunrise_time)
        //private val sunsetTimeView: TextView = view.findViewById(R.id.sunset_time)

        //private val highTimeView: TextView = view.findViewById(R.id.high_temp)
        //private val lowTimeView: TextView = view.findViewById(R.id.low_temp)
        //private val currentTimeView: TextView = view.findViewById(R.id.curr_temp)

        //private var conditionIcon: ImageView = view.findViewById(R.id.condition_icon)


        @SuppressLint("ResourceType")
        fun bind(data: DayForecast) {
            //we need to state what information of data should be used for which element of our recycler view item.
            itemBinding.highTemp.text = "High: ${data.temp.max.toInt()}°"
            itemBinding.lowTemp.text = "Low: ${data.temp.min.toInt()}°"
            itemBinding.currTemp.text = "Temp: ${data.temp.day.toInt()}°"

            //highTimeView.text = "High: ${data.temp.max.toInt()}°"
            //lowTimeView.text = "Low: ${data.temp.min.toInt()}°"
            //currentTimeView.text = "Temp: ${data.temp.day.toInt()}°"


            val instant = Instant.ofEpochSecond(data.dt)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            itemBinding.date.text = dateFormatter.format(dateTime)
            //dateView.text = dateFormatter.format(dateTime)

            val instant2 = Instant.ofEpochSecond(data.sunrise)
            val sunriseTime = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault())
            itemBinding.sunriseTime.text = "Sunrise: ${timeFormatter.format(sunriseTime)}"
            //sunriseTimeView.text = "Sunrise: ${timeFormatter.format(sunriseTime)}"

            val instant3 = Instant.ofEpochSecond(data.sunset)
            val sunsetTime = LocalDateTime.ofInstant(instant3, ZoneId.systemDefault())
            itemBinding.sunsetTime.text = "Sunrise: ${timeFormatter.format(sunsetTime)}"
            //sunsetTimeView.text = "Sunrise: ${timeFormatter.format(sunsetTime)}"

            val iconName = data.weather.firstOrNull()?.icon
            val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
            Glide.with(itemBinding.conditionIcon.context)
                .load(iconUrl)
                .into(itemBinding.conditionIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowDateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        //https://youtu.be/pMLmVJqL_ys?t=974

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_date, parent, false)
//        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dayForecastData[position])
    }

    override fun getItemCount() = dayForecastData.size
}