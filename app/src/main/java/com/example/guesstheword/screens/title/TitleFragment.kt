package com.example.guesstheword.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentTitleBinding
import com.example.guesstheword.screens.game.GameFragment

class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.playGameButton.setOnClickListener {
            val mFragmentManager = fragmentManager
            val mGameFragment = GameFragment()
            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mGameFragment, GameFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        return binding.root
    }
}