package com.jsbs87.android.omtest.app.data.entity

import com.google.gson.annotations.SerializedName

data class AttachmentEntity(
    @SerializedName("assetId")
    val assetId: String,
    @SerializedName("assetName")
    val assetName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("responseElementType")
    val responseElementType: String,
    @SerializedName("value")
    val value: String
)