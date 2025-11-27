package com.safetrace.safetraceapp.ui.journal

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.safetrace.safetraceapp.databinding.FragmentCameraBinding
import com.safetrace.safetraceapp.utils.FileHelper
import java.io.File

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private val args: CameraFragmentArgs by navArgs()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.CAMERA] == true && permissions[Manifest.permission.RECORD_AUDIO] == true) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Permissions de caméra refusées.", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        binding.btnCapture.setOnClickListener {
            if (args.isPhoto) {
                capturePhoto()
            } else {
                captureVideo()
            }
        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        requestPermissionLauncher.launch(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
        )
    }

    private fun startCamera() {
        // TODO: Implémentation réelle de CameraX
        binding.tvCameraStatus.text = "CameraX prêt. Mode: ${if (args.isPhoto) "Photo" else "Vidéo"}"
    }

    private fun capturePhoto() {
        // TODO: Logique de capture de photo avec CameraX
        val photoFile = FileHelper.createMediaFile(requireContext(), true)
        // Simulation de la sauvegarde
        val photoPath = photoFile.absolutePath
        Toast.makeText(requireContext(), "Photo capturée et sauvegardée.", Toast.LENGTH_SHORT).show()

        // Retourner au fragment AddEntryFragment avec le chemin du fichier
        findNavController().previousBackStackEntry?.savedStateHandle?.set("photoPath", photoPath)
        findNavController().popBackStack()
    }

    private fun captureVideo() {
        // TODO: Logique de capture de vidéo avec CameraX
        val videoFile = FileHelper.createMediaFile(requireContext(), false)
        // Simulation de la sauvegarde
        val videoPath = videoFile.absolutePath
        Toast.makeText(requireContext(), "Vidéo capturée et sauvegardée.", Toast.LENGTH_SHORT).show()

        // Retourner au fragment AddEntryFragment avec le chemin du fichier
        findNavController().previousBackStackEntry?.savedStateHandle?.set("videoPath", videoPath)
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
