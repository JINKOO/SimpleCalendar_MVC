package com.kjk.mvc_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kjk.mvc_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayoutValues()
    }

    private fun initLayoutValues() {
        setContentView(binding.root)
    }
}