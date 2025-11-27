package com.safetrace.safetraceapp.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.safetrace.safetraceapp.ViewModelFactory
import com.safetrace.safetraceapp.databinding.FragmentJournalBinding

class JournalFragment : Fragment() {

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!
    private lateinit var journalViewModel: JournalViewModel
    private lateinit var adapter: JournalEntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        journalViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(JournalViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        journalViewModel.allEntries.observe(viewLifecycleOwner) { entries ->
            adapter.submitList(entries)
        }

        binding.fabAddEntry.setOnClickListener {
            // Naviguer vers le fragment d'ajout d'entrée
            findNavController().navigate(JournalFragmentDirections.actionJournalFragmentToAddEntryFragment())
        }
    }

    private fun setupRecyclerView() {
        adapter = JournalEntryAdapter()
        binding.rvJournalEntries.layoutManager = LinearLayoutManager(context)
        binding.rvJournalEntries.adapter = adapter
        binding.rvJournalEntries.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
