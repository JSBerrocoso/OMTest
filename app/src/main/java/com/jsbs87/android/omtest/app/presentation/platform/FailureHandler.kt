package com.jsbs87.android.omtest.app.presentation.platform

import com.jsbs87.android.omtest.app.domain.exception.Failure

interface FailureHandler {
    fun onFailure(failure: Failure?)
}