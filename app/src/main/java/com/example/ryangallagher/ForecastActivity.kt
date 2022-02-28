package com.example.ryangallagher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ryangallagher.databinding.ActivityForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/* CHECKLIST
 * []thoroughly document everything.
 */

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    @Inject lateinit var viewModel: ForecastViewModel
    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val actionBar = supportActionBar

        if (actionBar != null) {             // actionBar!!.title = "Forecast" accomplishes the same thing.
            actionBar.title = "Forecast"
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.forecast.observe(this) {forecast ->
            binding.recyclerView.adapter = MyAdapter(forecast.list)
        }
        viewModel.loadData()
    }
}