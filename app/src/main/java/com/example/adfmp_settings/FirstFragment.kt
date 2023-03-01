package com.example.adfmp_settings

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.adfmp_settings.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.statisticsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_statisticsScreen)
        }

        getView()?.findViewById<Button>(R.id.button_play_bot)?.setOnClickListener {
            val intentToGameActivity = Intent(requireContext(), GameActivity::class.java)
            // todo: spawn a modal dialog, put name as extra to Intent
            // todo: setup bot
            startActivity(intentToGameActivity)
        }

        getView()?.findViewById<Button>(R.id.button_play_hotseat)?.setOnClickListener {
            val intentToGameActivity = Intent(requireContext(), GameActivity::class.java)
            // todo: spawn a modal dialog, put name as extra to Intent x2
            startActivity(intentToGameActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}