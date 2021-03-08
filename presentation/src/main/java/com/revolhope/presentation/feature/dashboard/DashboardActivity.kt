package com.revolhope.presentation.feature.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.revolhope.presentation.R
import com.revolhope.presentation.common.base.BaseActivity
import com.revolhope.presentation.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity()/*BaseActivity<ActivityDashboardBinding>()*/ {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    /*override val binding: ActivityDashboardBinding
        get() = ActivityDashboardBinding.inflate(layoutInflater)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        findNavController(R.id.nav_host_fragment).run {
            navController = this
            setupActionBarWithNavController(
                navController = this,
                configuration = AppBarConfiguration(
                    topLevelDestinationIds = setOf(
                        R.id.nav_home,
                        R.id.nav_gallery,
                        R.id.nav_slideshow
                    ),
                    drawerLayout = binding.drawerLayout
                ).also { appBarConfiguration = it }
            )
            /*findViewById<NavigationView>(R.id.nav_view).also {
                it.setupWithNavController(this)
            }*/
            binding.navView.setupWithNavController(this)
            binding.navView.setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_gallery -> {
                        Log.i("MainActivity", "nav_gallery!")
                    }
                    R.id.nav_home -> {
                        Log.i("MainActivity", "nav_home!")
                    }
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
}