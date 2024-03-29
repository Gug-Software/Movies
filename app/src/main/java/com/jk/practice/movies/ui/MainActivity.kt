package com.jk.practice.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.jk.practice.movies.R
import com.jk.practice.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
            ?: return
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.movies),
            null
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.bottomNavView.setupWithNavController(navController)

        binding.bottomNavView.visibility = View.GONE
        binding.toolbar.visibility = View.GONE

    }

    override fun onSupportNavigateUp(): Boolean {
        return host.navController.navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(host.navController)
                || super.onOptionsItemSelected(item)
    }
}