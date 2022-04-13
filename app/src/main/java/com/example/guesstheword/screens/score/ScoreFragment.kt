package com.example.guesstheword.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.ViewModelProvider
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentScoreBinding
import com.example.guesstheword.screens.game.GameFragment

class ScoreFragment : Fragment() {
    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    var EXTRA_SCORE = "extra_score"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ScoreViewModel::class.java)
        binding.scoreText.text = viewModel.score.toString()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val scoreArgument = arguments?.getString(EXTRA_SCORE)
            binding.scoreText.text = scoreArgument
        }
        binding.playAgainButton.setOnClickListener {
            val mFragmentManager = fragmentManager
            val mGameFragment = GameFragment()
            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mGameFragment, GameFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}