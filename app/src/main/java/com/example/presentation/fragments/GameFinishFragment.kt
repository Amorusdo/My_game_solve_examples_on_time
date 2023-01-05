package com.example.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mygame.R
import com.example.mygame.databinding.FragmentGameFinishBinding
import kotlin.system.exitProcess

class GameFinishFragment : Fragment() {
    private val args by navArgs<GameFinishFragmentArgs>()

    private val binding: FragmentGameFinishBinding by lazy {
        FragmentGameFinishBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        textBind()
    }

    private fun clickListeners() = binding.apply {
        btPlayAgaine.setOnClickListener { launchWelcomeFragment() }
        btOut.setOnClickListener { exitProcess(-1) }
    }

    private fun textBind() = binding.apply {
        args.gameResalt.apply {
            requireContext().resources.apply {
                val text = if (isWinner) getString(R.string.win)
                else getString(R.string.lose)
                txFinish.text = String.format(text, isWinner.toString())
                tvAllAnser.text = String.format(
                    getString(R.string.received_questions),
                    countOfQuestion.toString()
                )
                tvReceivedQestions.text = String.format(
                    getString(R.string.all_answer),
                    countOfRightAnswer.toString(),
                )
                tvNeedCorrectAnswer.text = String.format(
                    getString(R.string.user_answer_percent),
                    percentOfRightAnswer.toString()
                )
                tvPersentAllCorrectAnswer.text = String.format(
                    getString(R.string.answer_percent),
                    gameSettings.minCountOfRightAnswer.toString()
                )
            }
        }
    }

    private fun launchWelcomeFragment() {
        findNavController().popBackStack()
    }
}