package com.jsbs87.android.omtest.app.domain.model

import com.google.gson.annotations.SerializedName

data class Genre(
    val externalId: String,
    val id: Int,
    val name: String,
    val parentName: String,
    val responseElementType: String
)