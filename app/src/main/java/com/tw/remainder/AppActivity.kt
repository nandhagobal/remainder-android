package com.tw.remainder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tw.remainder.databinding.ActivityMainBinding

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}