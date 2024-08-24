package com.abadzheva.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameBinding
import com.abadzheva.composition.domain.entity.GameResult
import com.abadzheva.composition.domain.entity.GameSettings
import com.abadzheva.composition.domain.entity.Level

class GameFragment : Fragment(R.layout.fragment_game) {
    private val binding by viewBinding(FragmentGameBinding::bind)
    private lateinit var level: Level

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
        binding.tvOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    winner = true,
                    countOfRightAnswers = 2,
                    countOfQuestions = 5,
                    GameSettings(0, 0, 0, 0),
                ),
            )
        }
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment =
            GameFragment().apply {
                arguments =
                    Bundle().apply {
                        putParcelable(KEY_LEVEL, level)
                    }
            }
    }
}
