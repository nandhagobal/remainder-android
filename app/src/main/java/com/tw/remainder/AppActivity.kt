package com.tw.remainder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tw.remainder.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startKoin{
            androidLogger()
            androidContext(this@AppActivity)
            modules(appModule)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}