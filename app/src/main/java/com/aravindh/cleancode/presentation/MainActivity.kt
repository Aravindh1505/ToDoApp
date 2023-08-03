package com.aravindh.cleancode.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aravindh.cleancode.R
import com.aravindh.cleancode.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //setupToolbar()
    }

    /*private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        val navHostController = navHostFragment.navController
        val config = AppBarConfiguration(navHostController.graph)
        binding.toolbar.setupWithNavController(navHostController, config)
    }*/
}