package com.tw.remainder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.tw.remainder.databinding.ActivityMainBinding
import com.tw.remainder.fragments.HomeFragmentDirections

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.searchIcon.setOnClickListener {
            if (navController.currentDestination == navController.findDestination(R.id.allRemainderFragment)) {
                binding.appName.visibility = View.GONE
                binding.searchBar.visibility = View.VISIBLE
            } else
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToAllRemainderFragment())
        }
        setContentView(binding.root)
    }
}