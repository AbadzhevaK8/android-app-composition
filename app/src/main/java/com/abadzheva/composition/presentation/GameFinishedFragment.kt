package com.abadzheva.composition.presentation

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment(R.layout.fragment_game_finished) {
    private val binding by viewBinding(FragmentGameFinishedBinding::bind)
}
