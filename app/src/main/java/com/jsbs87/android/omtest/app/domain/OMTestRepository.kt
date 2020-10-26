package com.jsbs87.android.omtest.app.domain

import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.domain.functional.Either
import com.jsbs87.android.omtest.app.domain.model.Movie

interface OMTestRepository {

    suspend fun getFilms(): Either<Failure, List<Movie>>
}