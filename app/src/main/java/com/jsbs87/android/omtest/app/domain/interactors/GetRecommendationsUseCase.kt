package com.jsbs87.android.omtest.app.domain.interactors

import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Recommentation

class GetRecommendationsUseCase(private val repository: OMTestRepositoryImp) :
    UseCase<List<Recommentation>, GetRecommendationsUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Recommentation>> {
        return repository.getRecommendations(params.externalId)
    }

    class Params(val externalId: String)
}