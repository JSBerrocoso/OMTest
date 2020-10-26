package com.jsbs87.android.omtest.app.presentation.ui.films

import android.os.Bundle
import android.util.Log
import android.view.View
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.model.Film
import com.jsbs87.android.omtest.app.presentation.extension.observe
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import org.koin.android.scope.currentScope
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.android.viewmodel.scope.viewModel

class FilmsFragment : BaseFragment() {

    private val viewModel by lifecycleScope.viewModel<FilmsViewModel>(this)

    override fun layoutId(): Int = R.layout.fragment_films

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.films, ::handleFilms)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadFilms()
    }

    private fun handleFilms(films: List<Film>?) {
        Log.d("", films.toString())
    }
}