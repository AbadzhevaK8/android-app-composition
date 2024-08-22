package com.abadzheva.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        val callback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback,
        )
    }

    private fun parseArgs() {
        gameResult = requireArguments().getSerializable(KEY_GAME_RESULT) as GameResult
    }

    private fun retryGame() {
        requireActivity()
            .supportFragmentManager
            .popBackStack(
                GameFragment.NAME,
                FragmentManager.POP_BACK_STACK_INCLUSIVE,
            )
    }

    companion object {
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment =
            GameFinishedFragment().apply {
                arguments =
                    Bundle().apply {
                        putSerializable(KEY_GAME_RESULT, gameResult)
                    }
            }
    }
}
