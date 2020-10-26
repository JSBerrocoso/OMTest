package com.jsbs87.android.omtest.app.presentation.di

import com.jsbs87.android.omtest.app.presentation.ui.films.FilmsFragment
import com.jsbs87.android.omtest.app.presentation.ui.films.FilmsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    scope(named<FilmsFragment>()) {
        viewModel { FilmsViewModel(get()) }
    }
}
