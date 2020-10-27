package com.jsbs87.android.omtest.app.domain

import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie
import com.jsbs87.android.omtest.app.domain.model.Recommentation

interface OMTestRepository {

    suspend fun getMovies(): Either<Failure, List<Movie>>
    suspend fun getDetailMovie(externalId: String): Either<Failure, Movie>
    suspend fun getRecommendations(externalId: String): Either<Failure, List<Recommentation>>
    suspend fun saveMovieToFavorites(movie: Movie): Either<Failure, Boolean>
    suspend fun deleteFavoriteMovie(movie: Movie): Either<Failure, Boolean>
    suspend fun isMovieFavorite(movie: Movie): Either<Failure, Boolean>
    suspend fun getFavoriteMovies(): Either< Failure, List<Movie>>


}