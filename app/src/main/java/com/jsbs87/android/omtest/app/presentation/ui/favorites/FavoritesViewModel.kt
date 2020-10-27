package com.jsbs87.android.omtest.app.presentation.ui.favorites

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.*
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class FavoritesViewModel(
    private val getMovies: GetFavoriteMoviesUseCase,
    private val saveMovies: SaveFavoriteMoviesUseCase,
    private val deleteFavoriteMovie: DeleteFavoriteMovieUseCase
) : BaseViewModel() {

    var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadFavoriteMovies() {
        showLoading()
        getMovies(UseCase.None) {
            hideLoading()
            it.either(::handleFailure, ::handlerFavoriteMovies)
        }
    }

    private fun handlerFavoriteMovies(result: List<Movie>) {
        movies.value = result
    }

    private fun handleDeletedMovieFromFavorited(deleted: Boolean) {
        loadFavoriteMovies()
    }

    fun removeFromFavorite(movie: Movie) {
        deleteFavoriteMovie(DeleteFavoriteMovieUseCase.Params(movie)) {
            it.either(::handleFailure, ::handleDeletedMovieFromFavorited)
        }
    }
}