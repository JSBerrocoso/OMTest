package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Film

class GetFilmsUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<List<Film>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Film>> {
        return repository.getFilms()
    }
}