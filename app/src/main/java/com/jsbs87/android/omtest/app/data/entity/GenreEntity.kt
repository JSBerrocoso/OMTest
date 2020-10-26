package com.jsbs87.android.omtest.app.data.entity

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("externalId")
    val externalId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("parentName")
    val parentName: String,
    @SerializedName("responseElementType")
    val responseElementType: String
)