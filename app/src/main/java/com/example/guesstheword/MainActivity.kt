package com.example.guesstheword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.guesstheword.databinding.ActivityMainBinding
import com.example.guesstheword.screens.title.TitleFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val mTitleFragment = TitleFragment()
        val fragment = mFragmentManager.findFragmentByTag(TitleFragment::class.java.simpleName)

        if (fragment !is TitleFragment) {
            Log.d("Guess The Word","fragment name:" + TitleFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mTitleFragment, TitleFragment::class.java.simpleName)
                .commit()
        }
    }
}