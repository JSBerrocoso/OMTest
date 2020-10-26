package com.jsbs87.android.omtest.app.presentation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.presentation.dialog.LoadingDialog
import com.jsbs87.android.omtest.app.presentation.platform.BaseActivity
import com.jsbs87.android.omtest.app.presentation.util.LoadingHandler

class HomeActivity : BaseActivity() {


    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

}