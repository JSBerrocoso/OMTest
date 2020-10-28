package com.jsbs87.android.omtest.app.presentation.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.extension.hideLoading
import com.jsbs87.android.omtest.app.presentation.extension.showLoading
import com.jsbs87.android.omtest.app.presentation.util.TouchableMovieView

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId(), container, false)

    open fun handleLoading(loading: Triple<Boolean, Boolean?, Int?>?) {
        if (loading?.first == true) {
            showLoading(loading.third)
        } else {
            hideLoading(loading?.second, loading?.third)
        }
    }

    open fun showError(failure: Failure?) {
        when (failure) {
            is Failure.ServerError -> {
                hideLoading(false, R.string.server_error)
            }
            is Failure.NetworkConnection -> {
                hideLoading(false, R.string.network_error)
            }
            is Failure.MovieAlreadySaved -> {
                showSnackBar(getString(R.string.movie_already_saved))
            }
            is Failure.DeleteFavoriteMoviesError -> {
                showSnackBar(getString(R.string.error_delete_favorite_movie))
            }
            is Failure.GetFavoriteMoviesError -> {
                showSnackBar(getString(R.string.error_get_favo_movie))
            }
            else -> (activity as? BaseActivity)?.showError(failure)
        }
    }

    fun setTitleCollapsingToolbarLayout(title: String) {
        when (activity) {
            is AppCompatActivity -> (activity as AppCompatActivity).findViewById<CollapsingToolbarLayout>(
                R.id.toolbar_layout
            ).title = title
//                (activity as AppCompatActivity).supportActionBar?.setTitle(title)
        }
    }

    fun setTitleToolbar(title: String) {
        when (activity) {
            is AppCompatActivity -> (activity as AppCompatActivity).supportActionBar?.setTitle(title)
        }
    }

    fun hasCollapsingToolbarLayout(): Boolean {
        val toolbar =
            (activity as AppCompatActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        return toolbar != null
    }

    fun showSnackBar(text: String) {
        when (activity) {
            is BaseActivity -> (activity as BaseActivity).showSnackBar(text)
        }
    }

    internal fun onClickMovie(movie: Movie) {
        if (activity is TouchableMovieView) {
            (activity as TouchableMovieView).onClickMovie(movie)
        }
    }
}