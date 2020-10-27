package com.jsbs87.android.omtest.app.data.entity

import com.jsbs87.android.omtest.app.BuildConfig
import com.jsbs87.android.omtest.app.domain.model.Image

data class ImageEntity(
    val name: String,
    val value: String
) {
    fun toImage(): Image {
        return Image(name, BuildConfig.BASE_IMAGE_URL + value)
    }
}