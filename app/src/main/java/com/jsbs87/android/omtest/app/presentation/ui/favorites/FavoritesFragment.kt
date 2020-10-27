package com.jsbs87.android.omtest.app.presentation.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.adapter.FavoriteMoviesAdapter
import com.jsbs87.android.omtest.app.presentation.adapter.MoviesAdapter
import com.jsbs87.android.omtest.app.presentation.extension.failure
import com.jsbs87.android.omtest.app.presentation.extension.observe
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import com.jsbs87.android.omtest.app.presentation.ui.detail.DetailMovieActivity
import kotlinx.android.synthetic.main.fragment_favorites.*

import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.scope.viewModel

class FavoritesFragment : BaseFragment() {

    private val viewModel by lifecycleScope.viewModel<FavoritesViewModel>(this)

    // TODO CHANGE ADAPTER TO SUPPORT ADD_OR_REMOVE_FAVORITE
    private val movieAdapter =
        FavoriteMoviesAdapter({ movie ->
            activity?.let { DetailMovieActivity.openActivity(it, movie.externalId) }
        }, {
            viewModel.removeFromFavorite(it)
        })

    override fun layoutId(): Int = R.layout.fragment_favorites

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.movies, ::handleMovies)
        observe(viewModel.loading, ::handleLoading)
        failure(viewModel.failure, ::showError)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = movieAdapter
        }
        retry_btn.setOnClickListener {
            viewModel.loadFavoriteMovies()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFavoriteMovies()
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

}