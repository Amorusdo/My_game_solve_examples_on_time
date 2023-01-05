package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mygame.databinding.FragmentWelcomeBinding



class WelcomeFragment : Fragment() {



private val binding : FragmentWelcomeBinding by lazy {
    FragmentWelcomeBinding.inflate(layoutInflater)
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bTRules.setOnClickListener {
            launchGameRuleFragment()
        }
        binding.bTplay.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    private fun launchChooseLevelFragment() {
        findNavController().navigate(
           WelcomeFragmentDirections.actionWelcomeFragmentToChooseTaskFragment()
        )
    }

    private fun launchGameRuleFragment() {
       findNavController().navigate(
         WelcomeFragmentDirections.actionWelcomeFragmentToRulesGameFragment(
               "Правила Игры"
           )
       )
    }
}
