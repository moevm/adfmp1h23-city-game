package com.example.adfmp_settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.adfmp_settings.databinding.StatisticsScreenBinding

const val winRateBotKey = "winRateBot"
const val gamesAgainstBotKey = "gamesAgainstBot"
const val winsAgainstBotKey = "winsAgainstBot"

class StatisticsScreen : Fragment() {
    private var _binding: StatisticsScreenBinding? = null

    var statisticsStorage : SharedPreferences? = null
    var winRateBot : Int = 23
    var gamesAgainstBot : Int = 111
    var winsAgainstBot = 31

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        statisticsStorage = this.activity?.getSharedPreferences("Statistics", Context.MODE_PRIVATE)

        winRateBot = statisticsStorage?.getInt(winRateBotKey,23)!!
        gamesAgainstBot = statisticsStorage?.getInt(gamesAgainstBotKey,111)!!
        winsAgainstBot = statisticsStorage?.getInt(winsAgainstBotKey,31)!!

        _binding = StatisticsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winRateBotText : TextView = view.findViewById(R.id.winrateagainstbotpercent)
        winRateBotText.text = "$winRateBot%"

        val gamesAgaintsBotText : TextView = view.findViewById(R.id.gamesrateagainstbot)
        gamesAgaintsBotText.text = "$gamesAgainstBot"

        val winsAgainstBotText : TextView = view.findViewById(R.id.winrateagainstbot)
        winsAgainstBotText.text = "$winsAgainstBot"

        val clearStatButton : Button = view.findViewById(R.id.cleanStat)
        clearStatButton.setOnClickListener{
            winRateBot = 0
            gamesAgainstBot = 0
            winsAgainstBot = 0

            saveDataInt(winRateBotKey,winRateBot)
            saveDataInt(gamesAgainstBotKey,gamesAgainstBot)
            saveDataInt(winsAgainstBotKey,winsAgainstBot)

            winRateBotText.text = "$winRateBot%"
            gamesAgaintsBotText.text = "$gamesAgainstBot"
            winsAgainstBotText.text = "$winsAgainstBot"
        }

            binding.toolbarStats.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_statisticsScreen_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun saveDataInt(key: String, value: Int){
        val editor = statisticsStorage?.edit()
        editor?.putInt(key,value)
        editor?.apply()
    }
}