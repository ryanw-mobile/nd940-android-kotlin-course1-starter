package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber
import java.util.zip.Inflater


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()
        Timber.plant(Timber.DebugTree())
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Setup Navigation for this Activity
     */
    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            // Show the destination label as upper case
            binding.toolbar.title = destination.label.toString().uppercase()
        }
    }
}
