package com.example.adfmp_settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import com.example.adfmp_settings.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SettingsFragment : Fragment() {
    var isVibro : Boolean = false
    var isAutoComplete : Boolean = false
    var isAutoLoseExit : Boolean = false
    var TurnTimeInMinutes : Int = 1
    var CurrentWordsPackage : String = "Cities"

    var pref : SharedPreferences? = null

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        pref =  this.activity?.getSharedPreferences("Settings", Context.MODE_PRIVATE)

        isVibro = pref?.getBoolean("vibro", true)!!
        isAutoComplete = pref?.getBoolean("autoCompl", false)!!
        isAutoLoseExit = pref?.getBoolean("autoLose", false)!!
        TurnTimeInMinutes = pref?.getInt("turnTime", 1)!!
        CurrentWordsPackage = pref?.getString("currentPack", "Cities")!!

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxVibro : CheckBox = view.findViewById(R.id.checkbox_vibro)
        checkboxVibro.isChecked = isVibro

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        saveDataBool("vibro", isVibro)
        saveDataBool("autoCompl", isAutoComplete)
        saveDataBool("autoLose", isAutoLoseExit)
        saveDataInt("turnTime", TurnTimeInMinutes)
        saveDataStr("currentPack", CurrentWordsPackage)
    }

    fun saveDataBool(key: String, value: Boolean){
        val editor = pref?.edit()
        editor?.putBoolean(key,value)
        editor?.apply()
    }

    fun saveDataInt(key: String, value: Int){
        val editor = pref?.edit()
        editor?.putInt(key,value)
        editor?.apply()
    }

    fun saveDataStr(key: String, value: String){
        val editor = pref?.edit()
        editor?.putString(key,value)
        editor?.apply()
    }
}