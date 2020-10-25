package com.jsbs87.android.omtest.app.presentation.util

interface LoadingHandler {
    fun showLoading(msg: Int? = null)
    fun hideLoading(success: Boolean? = null, msg: Int? = null)
}