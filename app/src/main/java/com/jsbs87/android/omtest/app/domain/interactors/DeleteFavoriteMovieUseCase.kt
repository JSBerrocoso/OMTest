package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

class DeleteFavoriteMovieUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<Boolean, DeleteFavoriteMovieUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return repository.deleteFavoriteMovie(params.movie)
    }

    class Params(val movie: Movie)
}