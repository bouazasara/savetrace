package com.safetrace.safetraceapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.safetrace.safetraceapp.R
import com.safetrace.safetraceapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardJournal.setOnClickListener {
            findNavController().navigate(R.id.journalFragment)
        }

        binding.cardHelp.setOnClickListener {
            findNavController().navigate(R.id.helpFragment)
        }

        binding.cardPositive.setOnClickListener {
            findNavController().navigate(R.id.positiveFragment)
        }

        binding.cardSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
