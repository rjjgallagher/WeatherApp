package com.example.ryangallagher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ryangallagher.databinding.CurrentConditionsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentConditionsFragment : Fragment() {

    private lateinit var binding: CurrentConditionsFragmentBinding
    private lateinit var viewModel: CurrentConditionsViewModel
    private val args: CurrentConditionsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentConditionsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Current Conditions"
        viewModel = CurrentConditionsViewModel()
        binding.forecastButton.setOnClickListener {
            val arguments = CurrentConditionsFragmentDirections.currentToForecast(args.zipCodeArg, args.latArg, args.lonArg) //not sure if this is what I want.
            Navigation.findNavController(it).navigate(arguments)
        }
    }

    //(args.currentConditionsArgs!!)
    override fun onResume() {
        super.onResume()
        viewModel.currentConditions.observe(this) { currentConditions ->
            bindData(currentConditions)
        }
        viewModel.loadData(args.currentConditionsArg!!)
    }

    private fun bindData(currentConditions: CurrentConditions) {
        binding.cityName.text = currentConditions.name
        binding.temperature.text = getString(
            R.string.temperature,
            currentConditions.main.temp.toInt()
        ) //the "d" in %1$d wants an integer, so we cast temp to an int with .toInt()
        binding.feelsLike.text =
            getString(R.string.feels_like, currentConditions.main.feelsLike.toInt())
        binding.high.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
        binding.low.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
        binding.humidity.text =
            getString(R.string.humidity, currentConditions.main.humidity.toInt())
        binding.pressure.text =
            getString(R.string.pressure, currentConditions.main.pressure.toInt())

        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(binding.conditionIcon)
    }
}