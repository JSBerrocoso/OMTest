package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

class SaveFavoriteMoviesUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<Boolean, SaveFavoriteMoviesUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Boolean> {
        return repository.saveMovieToFavorites(params.movie)
    }

    class Params(val movie: Movie)
}