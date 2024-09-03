package com.abadzheva.composition.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment(R.layout.fragment_game_finished) {
    private val args by navArgs<GameFinishedFragmentArgs>()

    private val binding by viewBinding(FragmentGameFinishedBinding::bind)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        binding.gameResult = args.gameResult
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}
