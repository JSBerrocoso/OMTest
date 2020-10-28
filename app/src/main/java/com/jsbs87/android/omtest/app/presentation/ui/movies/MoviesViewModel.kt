package com.jsbs87.android.omtest.app.presentation.ui.movies

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.omtest.app.domain.interactors.GetMoviesUseCase
import com.jsbs87.android.omtest.app.domain.interactors.UseCase
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.presentation.platform.BaseViewModel

class MoviesViewModel(private val getMovies: GetMoviesUseCase) : BaseViewModel() {

    var filteredElements: MutableLiveData<List<Movie>> = MutableLiveData()
    var films: MutableLiveData<List<Movie>> = MutableLiveData()
    var searchText: String = ""
        set(value) {
            field = value
            filterData()
        }

    private fun filterData() {
        if (searchText.isEmpty()) {
            filteredElements.value = films.value
            filteredElements.postValue(filteredElements.value)
            return
        }
        filteredElements.value = films.value?.filter { it.name.contains(searchText) }
        filteredElements.postValue(filteredElements.value)
    }


    fun loadMovies() {
        showLoading()
        getMovies(UseCase.None) {
            hideLoading()
            it.either(::handleFailure, ::handlerFilms)
        }
    }

    private fun handlerFilms(result: List<Movie>) {
        films.value = result
    }
}