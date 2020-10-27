package com.jsbs87.android.omtest.app.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : BaseActivity() {

    companion object {
        fun openActivity(context: Context, externalId: String) {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieFragment.EXTRA_EXTERNAL_ID, externalId)
            context.startActivity(intent)
        }
    }

    override fun layoutId(): Int = R.layout.activity_detail_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setOnBackButtonToolbar(true, toolbar)

        if (intent.hasExtra(DetailMovieFragment.EXTRA_EXTERNAL_ID)) {
            val externalId = intent.getStringExtra(DetailMovieFragment.EXTRA_EXTERNAL_ID)
            val detail = DetailMovieFragment.newInstance(externalId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_detail, detail)
                .commit()
        }
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

}