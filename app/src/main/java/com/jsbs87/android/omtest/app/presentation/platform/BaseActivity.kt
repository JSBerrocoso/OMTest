package com.jsbs87.android.omtest.app.presentation.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.presentation.util.LoadingHandler

abstract class BaseActivity : AppCompatActivity(), LoadingHandler {

    internal fun showAlertDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { dialog, which -> }
            .show()
    }

    open fun showError(failure: Failure?) {
        failure?.message?.let { showAlertDialog("Error", it) }
    }
}
