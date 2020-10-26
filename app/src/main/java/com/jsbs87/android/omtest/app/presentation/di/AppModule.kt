package com.jsbs87.android.omtest.app.presentation.di

import com.jsbs87.android.omtest.app.data.utils.NetworkHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val androidModule = module {
    single { NetworkHandler(androidApplication()) }
}

