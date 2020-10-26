package com.jsbs87.android.omtest.app.presentation.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.presentation.extension.hideLoading
import com.jsbs87.android.omtest.app.presentation.extension.showLoading

abstract class BaseFragment: Fragment() {

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId(), container, false)

    open fun handleLoading(loading: Triple<Boolean, Boolean?, Int?>?) {
        if (loading?.first == true) {
            showLoading(loading.third)
        } else {
            hideLoading(loading?.second, loading?.third)
        }
    }

    open fun showError(failure: Failure?) {
        when (failure) {
            is Failure.ServerError -> {
                hideLoading(false, R.string.server_error)
            }
            is Failure.NetworkConnection -> {
                hideLoading(false, R.string.network_error)
            }
            else-> (activity as? BaseActivity)?.showError(failure)
        }
    }
}