package com.jsbs87.android.omtest.app.data.entity

import com.google.gson.annotations.SerializedName
import com.jsbs87.android.omtest.app.BuildConfig
import com.jsbs87.android.omtest.app.domain.model.Attachment

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
) {
    fun toAttachment(): Attachment {
        return Attachment(assetId, assetName, name, responseElementType, BuildConfig.BASE_IMAGE_URL + value)
    }
}