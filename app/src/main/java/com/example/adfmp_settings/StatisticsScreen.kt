package com.example.adfmp_settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.adfmp_settings.databinding.StatisticsScreenBinding



class StatisticsScreen : Fragment() {
    private var _binding: StatisticsScreenBinding? = null

    var statisticsSorage : SharedPreferences? = null
    var winRateBot : Int = 0
    var gamesAgainstBot : Int = 0
    var winsAgainstBot = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = StatisticsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statisticsSorage = this.activity?.getSharedPreferences("Statistics", Context.MODE_PRIVATE)


        binding.toolbarStats.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_statisticsScreen_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}