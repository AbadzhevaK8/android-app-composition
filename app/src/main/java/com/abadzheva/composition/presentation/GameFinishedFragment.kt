package com.abadzheva.composition.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameFinishedBinding
import com.abadzheva.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment(R.layout.fragment_game_finished) {
    private lateinit var gameResult: GameResult

    private val binding by viewBinding(FragmentGameFinishedBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = super.onCreateView(inflater, container, savedInstanceState)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text =
                String.format(
                    getString(R.string.required_score),
                    gameResult.gameSettings.minCountOfRightAnswers,
                )
            tvScoreAnswers.text =
                String.format(
                    getString(R.string.score_answers),
                    gameResult.countOfRightAnswers,
                )
            tvRequiredPercentage.text =
                String.format(
                    getString(R.string.required_percentage),
                    gameResult.gameSettings.minPercentOfRightAnswers,
                )
            tvScorePercentage.text =
                String.format(
                    getString(R.string.score_percentage),
                    getPercentOfRightAnswers(),
                )
        }
    }

    private fun getSmileResId(): Int =
        if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }

    private fun getPercentOfRightAnswers() =
        with(gameResult) {
            if (countOfQuestions == 0) {
                0
            } else {
                ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
            }
        }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    companion object {
        const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment =
            GameFinishedFragment().apply {
                arguments =
                    Bundle().apply {
                        putParcelable(KEY_GAME_RESULT, gameResult)
                    }
            }
    }
}
