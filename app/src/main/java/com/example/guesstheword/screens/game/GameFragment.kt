package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding
import com.example.guesstheword.screens.score.ScoreFragment

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }
        updateScoreText()
        updateWordText()

        return binding.root
    }

    private fun onSkip() {
        viewModel.onSkip()
        updateWordText()
        updateScoreText()
    }
    private fun onCorrect() {
        viewModel.onCorrect()
        updateScoreText()
        updateWordText()
    }

    private fun updateWordText() {
        binding.wordText.text = viewModel.word
    }

    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val gScoreFragment = ScoreFragment()
        val gBundle = Bundle()
        val score = viewModel.score.toString()
        gBundle.putString(gScoreFragment.EXTRA_SCORE, score)
        gScoreFragment.arguments = gBundle
        val gFragmentManager = fragmentManager
        gFragmentManager?.beginTransaction()?.apply {
            replace(R.id.frame_container, gScoreFragment, ScoreFragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    private fun onEndGame() {
        gameFinished()
    }
}