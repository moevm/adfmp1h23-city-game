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

const val winRatePlayerKey = "winRatePlayer"
const val gamesAgainstPlayerKey = "gamesAgainstPlayer"
const val winsAgainstPlayerKey = "winsAgainstPlayer"

fun numToPercents(num : Int): String {
    return "$num%"
}

class StatisticsScreen : Fragment() {
    private var _binding: StatisticsScreenBinding? = null

    var statisticsStorage : SharedPreferences? = null
    var winRateBot : Int = 0
    var gamesAgainstBot : Int = 0
    var winsAgainstBot = 0

    var winRatePlayer : Int = 23
    var gamesAgainstPlayer : Int = 111
    var winsAgainstPlayer = 31

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        statisticsStorage = this.activity?.getSharedPreferences("Statistics", Context.MODE_PRIVATE)

        winRateBot = statisticsStorage?.getInt(winRateBotKey,0)!!
        gamesAgainstBot = statisticsStorage?.getInt(gamesAgainstBotKey,0)!!
        winsAgainstBot = statisticsStorage?.getInt(winsAgainstBotKey,0)!!

        winRatePlayer = statisticsStorage?.getInt(winRatePlayerKey,22)!!
        gamesAgainstPlayer = statisticsStorage?.getInt(gamesAgainstPlayerKey,222)!!
        winsAgainstPlayer = statisticsStorage?.getInt(winsAgainstPlayerKey,333)!!

        _binding = StatisticsScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winRateBotText : TextView = view.findViewById(R.id.winrateagainstbotpercent)
        winRateBotText.text = numToPercents(winRateBot)

        val gamesAgaintsBotText : TextView = view.findViewById(R.id.gamesrateagainstbot)
        gamesAgaintsBotText.text = "$gamesAgainstBot"

        val winsAgainstBotText : TextView = view.findViewById(R.id.winrateagainstbot)
        winsAgainstBotText.text = "$winsAgainstBot"

        val winRatePlayerText : TextView = view.findViewById(R.id.winrateagainstplayer)
        winRatePlayerText.text = numToPercents(winRatePlayer)

        val gamesAgaintsPlayerText : TextView = view.findViewById(R.id.gamesagainstplayer)
        gamesAgaintsPlayerText.text = "$gamesAgainstPlayer"

        val winsAgainstPlayerText : TextView = view.findViewById(R.id.winsagainstplayer)
        winsAgainstPlayerText.text = "$winsAgainstPlayer"

        val clearStatButton : Button = view.findViewById(R.id.cleanStat)
        clearStatButton.setOnClickListener{
            winRateBot = 0
            gamesAgainstBot = 0
            winsAgainstBot = 0

            saveDataInt(winRateBotKey,winRateBot)
            saveDataInt(gamesAgainstBotKey,gamesAgainstBot)
            saveDataInt(winsAgainstBotKey,winsAgainstBot)

            winRateBotText.text = numToPercents(winRateBot)
            gamesAgaintsBotText.text = "$gamesAgainstBot"
            winsAgainstBotText.text = "$winsAgainstBot"

            winRatePlayer = 0
            gamesAgainstPlayer = 0
            winsAgainstPlayer = 0

            saveDataInt(winRatePlayerKey,winRatePlayer)
            saveDataInt(gamesAgainstPlayerKey,gamesAgainstPlayer)
            saveDataInt(winsAgainstPlayerKey,winsAgainstPlayer)

            winRatePlayerText.text = numToPercents(winRatePlayer)
            gamesAgaintsPlayerText.text = "$gamesAgainstPlayer"
            winsAgainstPlayerText.text = "$winsAgainstPlayer"
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