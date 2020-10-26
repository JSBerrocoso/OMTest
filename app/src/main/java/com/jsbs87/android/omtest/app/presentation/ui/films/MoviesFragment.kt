package com.jsbs87.android.omtest.app.presentation.ui.films

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.presentation.adapter.MoviesAdapter
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.extension.failure
import com.jsbs87.android.omtest.app.presentation.extension.observe
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import com.jsbs87.android.omtest.app.presentation.platform.FailureHandler
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class MoviesFragment : BaseFragment() {

    private val viewModel by lifecycleScope.viewModel<MoviesViewModel>(this)

    private val movieAdapter =
        MoviesAdapter { movie, position ->

        }

    override fun layoutId(): Int = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.films, ::handleFilms)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = movieAdapter
        }
        viewModel.loadFilms()
    }

    private fun handleFilms(movies: List<Movie>?) {
        if (movies?.isEmpty()!!) {
            showEmptyViews()
        } else {
            showMoviesViews()
            movieAdapter.addAll(movies)
        }
    }

    private fun showMoviesViews() {
        empty_movies_container.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    private fun showEmptyViews() {
        empty_movies_container.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }
}