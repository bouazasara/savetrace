package com.safetrace.safetraceapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.safetrace.safetraceapp.ViewModelFactory
import com.safetrace.safetraceapp.data.model.User
import com.safetrace.safetraceapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveRegistration.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.etName.text.toString().trim()
        val firstName = binding.etFirstname.text.toString().trim()
        val ageText = binding.etAge.text.toString().trim()
        val phoneNumber = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val emergencyContact = binding.etEmergencyContact.text.toString().trim()
        val password = binding.etPasswordSetup.text.toString().trim()

        if (name.isEmpty() || firstName.isEmpty() || ageText.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || emergencyContact.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show()
            return
        }

        val age = ageText.toIntOrNull()
        if (age == null || age <= 0) {
            Toast.makeText(requireContext(), "Âge invalide.", Toast.LENGTH_LONG).show()
            return
        }

        val newUser = User(
            name = name,
            firstName = firstName,
            age = age,
            phoneNumber = phoneNumber,
            address = address,
            emergencyContactNumber = emergencyContact
        )

        userViewModel.registerUser(newUser, password)
        Toast.makeText(requireContext(), "Inscription réussie. Bienvenue !", Toast.LENGTH_SHORT).show()
        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
