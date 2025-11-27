package com.safetrace.safetraceapp.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.safetrace.safetraceapp.ViewModelFactory
import com.safetrace.safetraceapp.data.model.JournalEntry
import com.safetrace.safetraceapp.databinding.FragmentAddEntryBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEntryFragment : Fragment() {

    private var _binding: FragmentAddEntryBinding? = null
    private val binding get() = _binding!!
    private lateinit var journalViewModel: JournalViewModel

    private var photoPath: String? = null
    private var videoPath: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEntryBinding.inflate(inflater, container, false)
        journalViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(JournalViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Afficher la date et l'heure actuelles
        val sdf = SimpleDateFormat("dd MMMM yyyy - HH:mm", Locale.getDefault())
        binding.tvDate.text = sdf.format(Date())

        binding.btnSaveEntry.setOnClickListener {
            saveEntry()
        }

        binding.btnAddPhoto.setOnClickListener {
            findNavController().navigate(AddEntryFragmentDirections.actionAddEntryFragmentToCameraFragment(isPhoto = true))
        }

        binding.btnAddVideo.setOnClickListener {
            findNavController().navigate(AddEntryFragmentDirections.actionAddEntryFragmentToCameraFragment(isPhoto = false))
        }

        // Récupérer les chemins de fichiers si on revient du fragment Camera
        arguments?.let {
            photoPath = it.getString("photoPath")
            videoPath = it.getString("videoPath")
            updateMediaStatus()
        }
    }

    private fun updateMediaStatus() {
        val status = mutableListOf<String>()
        photoPath?.let { status.add("Photo jointe") }
        videoPath?.let { status.add("Vidéo jointe") }

        if (status.isNotEmpty()) {
            binding.tvMediaStatus.text = status.joinToString(" | ")
            binding.tvMediaStatus.visibility = View.VISIBLE
        } else {
            binding.tvMediaStatus.visibility = View.GONE
        }
    }

    private fun saveEntry() {
        val text = binding.etJournalText.text.toString().trim()

        if (text.isEmpty() && photoPath == null && videoPath == null) {
            Toast.makeText(requireContext(), "Veuillez écrire quelque chose ou ajouter une preuve.", Toast.LENGTH_LONG).show()
            return
        }

        val entry = JournalEntry(
            text = text,
            photoPath = photoPath,
            videoPath = videoPath
        )

        journalViewModel.insertEntry(entry)
        Toast.makeText(requireContext(), "Entrée de journal sécurisée.", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
