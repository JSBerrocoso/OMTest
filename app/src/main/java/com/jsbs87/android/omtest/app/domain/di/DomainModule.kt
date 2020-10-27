package com.jsbs87.android.omtest.app.domain.di

import com.jsbs87.android.omtest.app.domain.interactors.*
import org.koin.dsl.module


val domainModule = module {
    factory { GetDetailMovieUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetRecommendationsUseCase(get()) }
    factory { GetFavoriteMoviesUseCase(get()) }
    factory { SaveFavoriteMoviesUseCase(get()) }
    factory { DeleteFavoriteMovieUseCase(get()) }
}
