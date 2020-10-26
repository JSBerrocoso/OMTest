package com.jsbs87.android.omtest.app.presentation.di

import com.jsbs87.android.omtest.app.presentation.ui.films.MoviesFragment
import com.jsbs87.android.omtest.app.presentation.ui.films.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    scope(named<MoviesFragment>()) {
        viewModel { MoviesViewModel(get()) }
    }
}
