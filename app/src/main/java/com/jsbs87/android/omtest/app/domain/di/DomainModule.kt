package com.jsbs87.android.omtest.app.domain.di

import com.jsbs87.android.omtest.app.domain.interactors.GetDetailMovieUseCase
import com.jsbs87.android.omtest.app.domain.interactors.GetMoviesUseCase
import com.jsbs87.android.omtest.app.domain.interactors.GetRecommendationsUseCase
import org.koin.dsl.module


val domainModule = module {
    factory { GetDetailMovieUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetRecommendationsUseCase(get()) }

}
