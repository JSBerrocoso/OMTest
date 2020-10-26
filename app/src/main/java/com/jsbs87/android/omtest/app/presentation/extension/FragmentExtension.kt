package com.jsbs87.android.omtest.app.presentation.extension

import com.jsbs87.android.omtest.app.presentation.platform.BaseActivity
import com.jsbs87.android.omtest.app.presentation.platform.BaseFragment
import com.jsbs87.android.omtest.app.presentation.util.LoadingHandler


fun BaseFragment.showLoading(msg: Int? = null) {
    if (activity is LoadingHandler) {
        (activity as LoadingHandler).showLoading()
    }
}

fun BaseFragment.hideLoading(success: Boolean? = null, msg: Int? = null) {
    if (activity is LoadingHandler) {
        (activity as LoadingHandler).hideLoading()
    }
}

fun BaseFragment.showDefaultError() {
    if ((activity is BaseActivity)) {
        (activity as BaseActivity).showGenericError()
    }
}