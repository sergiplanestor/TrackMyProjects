package com.revolhope.presentation.feature.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.View
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

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration



    override val binding: ActivityDashboardBinding
        get() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))

        findNavController(R.id.nav_host_fragment).run {
            navController = this
            setupActionBarWithNavController(
                navController = navController,
                configuration = AppBarConfiguration(
                    topLevelDestinationIds = setOf(
                        R.id.nav_home,
                        R.id.nav_gallery,
                        R.id.nav_slideshow
                    ),
                    drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout).also { drawerLayout = it }
                ).also { appBarConfiguration = it }
            )
            navView = findViewById<NavigationView>(R.id.nav_view).also {
                it.setupWithNavController(navController)
            }
        }

        /*supportFragmentManager.findFragmentById(R.id.nav_host_fragment).let {
            (it as? NavHostFragment)?.navController
        }?.run {
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
            binding.navView.setupWithNavController(this)
            navController = this
        }*/
    }

    /*override fun onBindViews() {
        super.onBindViews()
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment).let {
            (it as? NavHostFragment)?.navController
        }?.run {
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
            binding.navView.setupWithNavController(this)
            navController = this
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
}