package com.safetrace.safetraceapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.safetrace.safetraceapp.SafeTraceApplication
import com.safetrace.safetraceapp.ViewModelFactory
import com.safetrace.safetraceapp.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            checkPassword()
        }

        // Vérifier si l'utilisateur est déjà enregistré. Si non, naviguer vers l'enregistrement.
        // Cette vérification est déjà faite dans MainActivity, mais c'est une sécurité.
        lifecycleScope.launch {
            val isRegistered = (requireActivity().application as SafeTraceApplication).userRepository.isUserRegistered()
            if (!isRegistered) {
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegistrationFragment())
            }
        }
    }

    private fun checkPassword() {
        val password = binding.etPassword.text.toString()
        if (userViewModel.checkPassword(password)) {
            Toast.makeText(requireContext(), "Connexion réussie.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToHomeFragment())
        } else {
            Toast.makeText(requireContext(), "Mot de passe incorrect.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
