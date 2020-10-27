package com.jsbs87.android.omtest.app.presentation.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.presentation.extension.animateSearchToolbar
import com.jsbs87.android.omtest.app.presentation.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_films, R.id.navigation_favorite))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val mSearchItem = menu!!.findItem(R.id.search_action)
        MenuItemCompat.setOnActionExpandListener(
            mSearchItem,
            object : MenuItemCompat.OnActionExpandListener {
                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    if (mSearchItem.isActionViewExpanded) {
                        toolbar.animateSearchToolbar(1, containsOverflow = false, show = false)
                    }
                    return true
                }

                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    toolbar.animateSearchToolbar(1, containsOverflow = true, show = true)
                    return true
                }
            })
        return true
    }

}