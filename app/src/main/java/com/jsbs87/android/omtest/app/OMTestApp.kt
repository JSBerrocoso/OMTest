package com.jsbs87.android.omtest.app

import android.app.Application
import com.jsbs87.android.omtest.app.data.di.dataModule
import com.jsbs87.android.omtest.app.domain.di.domainModule
import com.jsbs87.android.omtest.app.presentation.di.appModule
import com.jsbs87.android.omtest.app.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OMTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(appModule, presentationModule, domainModule, dataModule))
        }
    }
}