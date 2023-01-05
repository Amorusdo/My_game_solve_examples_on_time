package com.example.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.models.GameResult
import com.example.mygame.R

import com.example.mygame.databinding.FragmentGameBinding
import com.example.presentation.viewModels.GameViewModels


class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()


    private val binding: FragmentGameBinding by lazy {
        FragmentGameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    private val viewModel by viewModels<GameViewModels>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGameSettings(args.level, args.type)
        observeViewModel()
        setClickers()
    }

    private fun observeViewModel() = binding.apply {
        viewModel.apply {
            question.observe(viewLifecycleOwner) { question ->
                tvSum.text = question.sum.toString()
                tvLeftNum.text = question.visibleNum.toString()
                tx1.text = question.variants[0].toString()
                tx2.text = question.variants[1].toString()
                tx3.text = question.variants[2].toString()
                tx4.text = question.variants[3].toString()
                tx5.text = question.variants[4].toString()
                tx6.text = question.variants[5].toString()
            }
            formattedTime.observe(viewLifecycleOwner) { time ->
                tvTimer.text = time
            }
            gameResult.observe(viewLifecycleOwner) {
                launchGameFragment(it)
            }
            percentOfCorrectAnswer.observe(viewLifecycleOwner) {
                initProgress(it.toInt())
            }
            enoughPercentOfRightAnswer.observe(viewLifecycleOwner) {
                progressBar.progressTintList = ColorStateList.valueOf(getProgressColor(it))
                tvAnswer.apply {
                    text = String.format(
                        requireContext().resources.getString(R.string.tv_answer_progress),
                        correctAnswers,
                        answer
                    )
                    setTextColor(getProgressColor(it))
                }
            }
        }
    }


    private fun setClickers() = binding.apply {
        tx1.setOnClickListener { onClick(it) }
        tx2.setOnClickListener { onClick(it) }
        tx3.setOnClickListener { onClick(it) }
        tx4.setOnClickListener { onClick(it) }
        tx5.setOnClickListener { onClick(it) }
        tx6.setOnClickListener { onClick(it) }
    }

    private fun launchGameFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToGameFinishFragment(gameResult)
        )
    }

    private fun onClick(view: View) {
        val tx: TextView = view as TextView
        viewModel.makeChoose(tx.text.toString().toInt())
    }

    @SuppressLint("ResourceAsColor")
    private fun initProgress(prog: Int) = binding.progressBar.apply {
        progress = prog
        secondaryProgress = viewModel.gameSettings.minPercentOfRightAnswer.toInt()
        progressTintList = ColorStateList.valueOf(R.color.hard_color)
    }

    private fun getProgressColor(isGreen: Boolean): Int {
        val color = if (isGreen) R.color.green
        else R.color.hard_color
        return ContextCompat.getColor(requireContext(), color)
    }

}










