package com.abadzheva.composition.presentation

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameBinding

class GameFragment : Fragment(R.layout.fragment_game) {
    private val binding by viewBinding(FragmentGameBinding::bind)
}
