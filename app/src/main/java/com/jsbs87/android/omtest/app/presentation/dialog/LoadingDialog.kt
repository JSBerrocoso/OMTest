package com.jsbs87.android.omtest.app.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jsbs87.android.omtest.app.R
import com.jsbs87.android.omtest.app.presentation.extension.visible
import kotlinx.android.synthetic.main.layout_loading.*

class LoadingDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.layout_loading, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        tv_animation.text = arguments?.getString(EXTRA_LOADING_MSG)
        loading_spinner.visible()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded) super.show(manager, tag)
        else Log.e(tag, "Error adding loading fragment")
    }

    fun hide() {
        dismiss()
    }

    companion object {
        private const val EXTRA_LOADING_MSG = "loadingMsg"

        fun newInstance(loadingMsg: String) = LoadingDialog().apply {
            arguments = Bundle().apply { putString(EXTRA_LOADING_MSG, loadingMsg) }
        }
    }
}