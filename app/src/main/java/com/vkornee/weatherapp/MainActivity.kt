package com.vkornee.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.city_selection -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigateUp()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}