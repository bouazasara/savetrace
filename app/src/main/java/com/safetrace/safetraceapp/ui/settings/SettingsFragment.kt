package com.safetrace.safetraceapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.safetrace.safetraceapp.ViewModelFactory
import com.safetrace.safetraceapp.databinding.FragmentSettingsBinding
import com.safetrace.safetraceapp.ui.auth.UserViewModel

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Afficher le contact d'urgence actuel
        binding.etEmergencyContactSettings.setText(userViewModel.getEmergencyContactNumber())

        binding.btnSaveEmergencyContact.setOnClickListener {
            saveEmergencyContact()
        }

        binding.btnChangePassword.setOnClickListener {
            // TODO: Implémenter la boîte de dialogue pour changer le mot de passe
            Toast.makeText(requireContext(), "Fonctionnalité de changement de mot de passe à implémenter.", Toast.LENGTH_SHORT).show()
        }

        binding.btnExportData.setOnClickListener {
            // TODO: Implémenter la fonction d'exportation de données
            Toast.makeText(requireContext(), "Fonctionnalité d'exportation de données à implémenter.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveEmergencyContact() {
        val newContact = binding.etEmergencyContactSettings.text.toString().trim()
        if (newContact.isNotEmpty()) {
            userViewModel.saveEmergencyContact(newContact)
            Toast.makeText(requireContext(), "Contact d'urgence mis à jour.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Le numéro de contact ne peut pas être vide.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
