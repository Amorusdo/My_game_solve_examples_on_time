package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.Level
import com.example.mygame.databinding.FragmentChooseLevelBinding


class ChooseLevelFragment : Fragment() {

    private val args by navArgs<ChooseLevelFragmentArgs>()

    private val binding: FragmentChooseLevelBinding by lazy {
        FragmentChooseLevelBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClikers()

    }

    private fun setOnClikers() {
        binding.apply {
            bTTest.setOnClickListener { launchGameRulesFragment(Level.TEST) }
            bTEasy.setOnClickListener { launchGameRulesFragment(Level.EASY) }
            bTMedium.setOnClickListener { launchGameRulesFragment(Level.STANDARD) }
            bTHard.setOnClickListener { launchGameRulesFragment(Level.HARD) }
        }
    }


    private fun launchGameRulesFragment(level: Level) = findNavController().navigate(
        ChooseLevelFragmentDirections.actionChoseLevelFragmentToGameFragment(level, args.type)
    )
}
