package com.safetrace.safetraceapp.ui.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.safetrace.safetraceapp.data.model.HelpContact
import com.safetrace.safetraceapp.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.btnAddCustomContact.setOnClickListener {
            // TODO: Implémenter la boîte de dialogue pour ajouter un contact personnalisé
        }
    }

    private fun setupRecyclerView() {
        val contacts = listOf(
            HelpContact("SOS Violences Conjugales", "3919", isEmergency = true),
            HelpContact("Police Secours", "17", isEmergency = true),
            HelpContact("Samu", "15", isEmergency = true),
            HelpContact("Association locale d'aide aux victimes", "01 23 45 67 89", isEmergency = false),
            HelpContact("Psychologue de garde", "06 98 76 54 32", isEmergency = false)
        )

        val adapter = HelpContactAdapter(contacts) { contact ->
            // Action d'appel
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${contact.number}")
            }
            startActivity(intent)
        }

        binding.rvHelpContacts.layoutManager = LinearLayoutManager(context)
        binding.rvHelpContacts.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
