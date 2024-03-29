package com.example.adfmp_settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.adfmp_settings.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val vibro = "vibro"
const val autoCompl = "autoCompl"
const val autoLose = "autoLose"
const val turnTime = "turnTime"
const val currentPack = "currentPack"
class SettingsFragment : Fragment() {
    var isVibro : Boolean = false
    var isAutoComplete : Boolean = false
    var isAutoLoseExit : Boolean = false
    var TurnTimeInMinutes : Int = 0
    var CurrentWordsPackage : Int = 0

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

        isVibro = pref?.getBoolean(vibro, false)!!
        isAutoComplete = pref?.getBoolean(autoCompl, false)!!
        isAutoLoseExit = pref?.getBoolean(autoLose, false)!!
        TurnTimeInMinutes = pref?.getInt(turnTime, 0)!!
        CurrentWordsPackage = pref?.getInt(currentPack, 0)!!

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val checkboxVibro : CheckBox = view.findViewById(R.id.checkbox_vibro)
        checkboxVibro.isChecked = isVibro
        checkboxVibro.setOnCheckedChangeListener { _, isChecked ->
            isVibro = isChecked
            saveDataBool(vibro,isChecked)
        }

        val checkboxAutoComplete : CheckBox = view.findViewById(R.id.checkbox_autocomplete)
        checkboxAutoComplete.isChecked = isAutoComplete
        checkboxAutoComplete.setOnCheckedChangeListener { _, isChecked ->
            isAutoComplete = isChecked
            saveDataBool(autoCompl,isChecked)
        }

        val checkboxAutoLose : CheckBox = view.findViewById(R.id.checkbox_lose)
        checkboxAutoLose.isChecked = isAutoLoseExit
        checkboxAutoLose.setOnCheckedChangeListener { _, isChecked ->
            isAutoLoseExit = isChecked
            saveDataBool(autoLose,isChecked)
        }

        val arrayOfSpinner : Array<String> = arrayOf("1 минута","30 секунд","Нет Таймера")
        //val timeArray : Array<Int> = arrayOf(60,30,0) // Время в секундах на ход для каждого варианта спинера
        val timeSpinner : Spinner = view.findViewById(R.id.spinner_timer)
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, arrayOfSpinner)
        timeSpinner.adapter = adapter
        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Всегда что-то выбрано, вроде
            }
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                TurnTimeInMinutes = position
                saveDataInt(turnTime, position)
            }
        }
        timeSpinner.setSelection(TurnTimeInMinutes)

        val spinnerDict : Spinner = view.findViewById(R.id.spinner_dictionary)
        val arrayOfPacks : Array<String> = arrayOf("Города","Болезни","Напитки")
        val adapterDict = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, arrayOfPacks)
        spinnerDict.adapter = adapterDict
        spinnerDict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Всегда что-то выбрано, вроде
            }
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long ) {
                CurrentWordsPackage = position
                saveDataInt(currentPack, position)
            }
        }
        spinnerDict.setSelection(CurrentWordsPackage)

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
        saveDataBool(vibro, isVibro)
        saveDataBool("autoCompl", isAutoComplete)
        saveDataBool("autoLose", isAutoLoseExit)
        saveDataInt("turnTime", TurnTimeInMinutes)
        saveDataInt("currentPack", CurrentWordsPackage)
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

}

