package com.jsbs87.android.omtest.app.presentation.ui.movies

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.GetMoviesUseCase
import com.jsbs87.android.omtest.app.domain.interactors.UseCase
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class MoviesViewModel(private val getMovies: GetMoviesUseCase) : BaseViewModel() {

    var films: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadFilms() {
        showLoading()
        getMovies(UseCase.None){
            hideLoading()
            it.either(::handleFailure, ::handlerFilms)
        }
    }

    private fun handlerFilms(result: List<Movie>) {
        films.value = result
    }
}