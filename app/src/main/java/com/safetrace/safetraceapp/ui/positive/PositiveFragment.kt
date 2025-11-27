package com.safetrace.safetraceapp.ui.positive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.safetrace.safetraceapp.data.model.PositiveContent
import com.safetrace.safetraceapp.databinding.FragmentPositiveBinding

class PositiveFragment : Fragment() {

    private var _binding: FragmentPositiveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPositiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contentList = listOf(
            PositiveContent("Chaque pas, même le plus petit, est un pas vers la guérison.", "La force n'est pas dans la capacité de faire, mais dans la volonté de continuer."),
            PositiveContent("Vous êtes plus forte que vous ne le pensez.", "Le courage n'est pas l'absence de peur, mais la capacité de la vaincre."),
            PositiveContent("Prenez soin de vous. Vous le méritez.", "Votre bien-être est votre priorité absolue. N'oubliez jamais cela."),
            PositiveContent("L'espoir est la seule chose plus forte que la peur.", "Même les nuits les plus sombres se terminent par le lever du soleil."),
            PositiveContent("Votre histoire est importante. Votre voix compte.", "Ne laissez jamais personne minimiser votre expérience.")
        )

        val adapter = PositiveContentAdapter(contentList)
        binding.viewPagerPositive.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
