package com.jsbs87.android.omtest.app.presentation.ui.movies

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.presentation.adapter.MoviesAdapter
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.extension.failure
import com.jsbs87.android.omtest.app.presentation.extension.observe
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import com.jsbs87.android.omtest.app.presentation.ui.detail.DetailMovieActivity
import com.jsbs87.android.omtest.app.presentation.util.SearcheableView
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class MoviesFragment : BaseFragment(), SearcheableView {

    private val viewModel by lifecycleScope.viewModel<MoviesViewModel>(this)

    private val movieAdapter =
        MoviesAdapter { movie ->
            activity?.let { DetailMovieActivity.openActivity(it, movie.externalId) }
        }

    override fun layoutId(): Int = R.layout.fragment_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.films, ::handleMovies)
        observe(viewModel.filteredElements, ::handleMovies)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = movieAdapter
        }
        viewModel.loadMovies()
    }

    private fun handleMovies(movies: List<Movie>?) {
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

    override fun search(newText: String) {
        viewModel.searchText = newText
    }
}