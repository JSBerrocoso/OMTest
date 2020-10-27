package com.jsbs87.android.omtest.app.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.*
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.Recommentation
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class DetailMovieViewModel(
    private val getFilms: GetDetailMovieUseCase,
    private val getRecommendations: GetRecommendationsUseCase,
    private val saveFavoriteMovie: SaveFavoriteMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : BaseViewModel() {

    var movie: MutableLiveData<Movie> = MutableLiveData()
    var recommendations: MutableLiveData<List<Recommentation>> = MutableLiveData()

    var externalId: MutableLiveData<String> = MutableLiveData()
    var externalContentId: MutableLiveData<String> = MutableLiveData()

    fun loadDetail() {
        showLoading()
        getFilms(GetDetailMovieUseCase.Params(externalId.value.toString())) {
            hideLoading()

            it.either(::handleFailure, ::handlerDetailMovie)
        }
    }

    fun loadRecommendations() {
        showLoading()
        getRecommendations(GetRecommendationsUseCase.Params(externalContentId.value.toString())) {
            hideLoading()
            it.either(::handleFailure, ::handleRecommendations)
        }
    }

    private fun handleRecommendations(resultRecommendations: List<Recommentation>) {
        recommendations.value = resultRecommendations
    }

    private fun handlerDetailMovie(resultMovie: Movie) {
        movie.value = resultMovie
        externalContentId.value = resultMovie.assetExternalId
        loadRecommendations()
    }

    fun saveToFavorite(movie: Movie) {
        saveFavoriteMovie(SaveFavoriteMoviesUseCase.Params(movie)) {
            it.either(::handleFailure, ::handleMovieFavorited)
        }
    }

    private fun handleMovieFavorited(saved: Boolean) {
        movie.value?.favorite = saved
        movie.postValue(movie.value)
    }

    private fun handleDeletedMovieFromFavorited(deleted: Boolean) {
        movie.value?.favorite = deleted.not()
        movie.postValue(movie.value)
    }

    fun removeFromFavorite(movie: Movie) {
        deleteFavoriteMovieUseCase(DeleteFavoriteMovieUseCase.Params(movie)) {
            it.either(::handleFailure, ::handleDeletedMovieFromFavorited)
        }
    }
}