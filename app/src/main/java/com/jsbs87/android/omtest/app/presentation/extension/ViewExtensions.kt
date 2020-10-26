package com.jsbs87.android.omtest.app.presentation.extension

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.jsbs87.android.omtest.app.R

fun View?.visible(visible: Boolean) {
    if (visible) this.visible()
    else this.gone()
}

fun View?.visible() {
    this ?: return
    this.visibility = View.VISIBLE
}

fun View?.invisible() {
    this ?: return
    this.visibility = View.INVISIBLE
}

fun View?.gone() {
    this ?: return
    this.visibility = View.GONE
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.color.grey_light)
        .into(this)
}