package com.jsbs87.android.omtest.app.presentation.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.domain.exception.Failure
import com.jsbs87.android.omtest.app.presentation.dialog.LoadingDialog
import com.jsbs87.android.omtest.app.presentation.util.LoadingHandler

abstract class BaseActivity : AppCompatActivity(), LoadingHandler {

    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        loadingDialog = LoadingDialog.newInstance(getString(R.string.loading))

    }

    abstract fun layoutId(): Int

    private fun showAlertDialog(title: String, message: String) {
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

    fun showGenericError() {
        showAlertDialog( getString(R.string.title_generic_error), getString(R.string.message_generic_error))
    }

    override fun showLoading() {
        loadingDialog.show(supportFragmentManager, loadingDialog.javaClass.simpleName)
    }

    override fun hideLoading() {
        loadingDialog.hide()
    }
}
