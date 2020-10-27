package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.entity.MovieEntity
import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

class GetDetailMovieUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<Movie, GetDetailMovieUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, Movie> {
        return repository.getDetailMovie(params.externalId)
    }

    class Params(val externalId: String)
}