package com.jsbs87.android.omtest.app.presentation.di

import com.jsbs87.android.omtest.app.presentation.ui.detail.DetailMovieFragment
import com.jsbs87.android.omtest.app.presentation.ui.detail.DetailMovieViewModel
import com.jsbs87.android.omtest.app.presentation.ui.favorites.FavoritesFragment
import com.jsbs87.android.omtest.app.presentation.ui.favorites.FavoritesViewModel
import com.jsbs87.android.omtest.app.presentation.ui.movies.MoviesFragment
import com.jsbs87.android.omtest.app.presentation.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    scope(named<MoviesFragment>()) {
        viewModel { MoviesViewModel(get()) }
    }

    scope(named<DetailMovieFragment>()) {
        viewModel { DetailMovieViewModel(get(), get(), get(), get()) }
    }

    scope(named<FavoritesFragment>()) {
        viewModel { FavoritesViewModel(get(), get(), get()) }
    }


}
