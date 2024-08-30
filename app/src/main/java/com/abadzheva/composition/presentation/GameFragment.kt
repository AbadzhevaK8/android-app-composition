package com.abadzheva.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.abadzheva.composition.R
import com.abadzheva.composition.databinding.FragmentGameBinding
import com.abadzheva.composition.domain.entity.GameResult
import com.abadzheva.composition.domain.entity.Level

class GameFragment : Fragment(R.layout.fragment_game) {
    private val args by navArgs<GameFragmentArgs>()
    private val binding by viewBinding(FragmentGameBinding::bind)

    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
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
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }

        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
    }

    private fun getColorByState(it: Boolean): Int {
        val colorResId =
            if (it) {
                android.R.color.holo_green_light
            } else {
                android.R.color.holo_red_light
            }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        val args =
            Bundle().apply {
                putParcelable(GameFinishedFragment.KEY_GAME_RESULT, gameResult)
            }
        findNavController().navigate(R.id.action_gameFragment_to_gameFinishedFragment, args)
    }

    companion object {
        const val KEY_LEVEL = "level"
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
