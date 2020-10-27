package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

class GetFavoriteMoviesUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<List<Movie>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Movie>> {
        return repository.getFavoriteMovies()
    }
}