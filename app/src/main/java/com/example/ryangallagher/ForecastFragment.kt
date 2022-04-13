package com.example.ryangallagher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ryangallagher.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private val args: ForecastFragmentArgs by this.navArgs()
    private lateinit var binding: FragmentForecastBinding
    @Inject
    lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForecastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Forecast"
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        viewModel.forecast.observe(this) { forecast ->
            binding.recyclerView.adapter = MyAdapter(forecast.list)
        }
        //if zip is null -> do lat lon api call. else do line 45 call
        if(args.zipCodeArg == null) {
            viewModel.loadData(args.latArg, args.lonArg)
        } else {
            viewModel.loadData(args.zipCodeArg)
        }
    }
}