package com.jsbs87.android.omtest.app.presentation.ui.films

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.GetFilmsUseCase
import com.jsbs87.android.omtest.app.domain.interactors.UseCase
import com.jsbs87.android.omtest.app.domain.model.Film
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class FilmsViewModel(private val getFilms: GetFilmsUseCase) : BaseViewModel() {

    var films: MutableLiveData<List<Film>> = MutableLiveData()

    fun loadFilms() {
        getFilms(UseCase.None){
            it.either(::handleFailure, ::handlerFilms)
        }
    }

    private fun handlerFilms(result: List<Film>) {
        films.value = result
    }
}