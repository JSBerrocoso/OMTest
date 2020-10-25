package com.jsbs87.android.omtest.app.presentation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.presentation.platform.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_films, R.id.navigation_favorite))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun showLoading(msg: Int?) {

    }

    override fun hideLoading(success: Boolean?, msg: Int?) {

    }

}