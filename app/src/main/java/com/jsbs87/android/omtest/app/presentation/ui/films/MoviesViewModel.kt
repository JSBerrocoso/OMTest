package com.jsbs87.android.omtest.app.presentation.ui.films

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.GetFilmsUseCase
import com.jsbs87.android.omtest.app.domain.interactors.UseCase
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class MoviesViewModel(private val getFilms: GetFilmsUseCase) : BaseViewModel() {

    var films: MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadFilms() {
        showLoading()
        getFilms(UseCase.None){
            hideLoading()
            it.either(::handleFailure, ::handlerFilms)
        }
    }

    private fun handlerFilms(result: List<Movie>) {
        films.value = result
    }
}