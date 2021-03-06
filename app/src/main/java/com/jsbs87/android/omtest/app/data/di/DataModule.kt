@file:Suppress("DEPRECATION")

package com.jsbs87.android.omtest.app.data.di

import android.preference.PreferenceManager
import com.jsbs87.android.omtest.app.BuildConfig
import com.jsbs87.android.omtest.app.data.api.OMTestApiService
import com.jsbs87.android.omtest.app.data.local.LocalDataSource
import com.jsbs87.android.omtest.app.data.local.LocalStorage
import com.jsbs87.android.omtest.app.data.repository.OMTestRepositoryImp
import com.jsbs87.android.omtest.app.domain.OMTestRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getOkHttpClient(httpLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(OMTestApiService::class.java)
    } bind OMTestApiService::class

    single {
        OMTestRepositoryImp(
            apiService = get(),
            networkHandler = get(),
            dataSource = get()
        )
    } bind OMTestRepository::class

    single {
        LocalStorage(
            PreferenceManager.getDefaultSharedPreferences(androidApplication())
        )
    } bind LocalDataSource::class
}

fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun httpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = okhttp3.logging.HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return httpLoggingInterceptor

}

