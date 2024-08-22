package com.abadzheva.composition.presentation

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : Fragment(R.layout.fragment_choose_level) {
    private val binding by viewBinding(FragmentChooseLevelBinding::bind)

    companion object {
        fun newInstance() = ChooseLevelFragment()
    }
}
