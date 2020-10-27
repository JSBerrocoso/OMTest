package com.jsbs87.android.omtest.app.data.local

import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

interface LocalDataSource {

    fun saveMovie(movie: Movie): Either<Failure, Boolean>
    fun isMovieSaved(movie: Movie): Boolean
    fun removeMovieSaved(movie: Movie): Boolean
    fun getFavoriteMovies(): List<Movie>

}