package com.jsbs87.android.omtest.app.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.GetDetailMovieUseCase
import com.jsbs87.android.omtest.app.domain.interactors.GetRecommendationsUseCase
import com.jsbs87.android.omtest.app.domain.interactors.UseCase
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.Recommentation
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class DetailMovieViewModel(
    private val getFilms: GetDetailMovieUseCase,
    private val getRecommendations: GetRecommendationsUseCase
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

    fun loadRecommendations(){
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
}