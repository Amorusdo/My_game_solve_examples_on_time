package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mygame.databinding.FragmentGameRulesBinding


     class GameRulesFragment : Fragment() {
       private val args by navArgs<GameRulesFragmentArgs>()
       private val binding:FragmentGameRulesBinding by lazy {
    FragmentGameRulesBinding.inflate(layoutInflater)

}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txRuleInGameFragment.text = args.lableFromWelcomFagmentToRuls
        binding.bTBackToWelcome.setOnClickListener {
            findNavController().navigate(
                GameRulesFragmentDirections.actionRulesGameFragmentToWelcomeFragment(


                )
            )
        }
    }
}






