package com.jsbs87.android.omtest.app.domain.di

import com.jsbs87.android.omtest.app.domain.interactors.GetFilmsUseCase
import org.koin.dsl.module


val domainModule = module {
    factory { GetFilmsUseCase(get()) }
}
