package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.usecases.Type
import com.example.mygame.databinding.FragmentChooseTaskBinding


class ChooseTaskFragment : Fragment() {

    private val binding: FragmentChooseTaskBinding by lazy {
        FragmentChooseTaskBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClikers()
    }

    private fun setOnClikers() = binding.apply {
        bTP.setOnClickListener { launchChooseLevelFragment(Type.PLUS) }
        bTM.setOnClickListener { launchChooseLevelFragment(Type.MINUS) }
        bTMu.setOnClickListener { launchChooseLevelFragment(Type.MULTIPLY) }
        bTD.setOnClickListener { launchChooseLevelFragment(Type.DELETE)}
    }

    private fun launchChooseLevelFragment(type: Type) {
        findNavController().navigate(
            ChooseTaskFragmentDirections.actionChooseTaskFragmentToChoseLevelFragment(type)
        )
    }
}
