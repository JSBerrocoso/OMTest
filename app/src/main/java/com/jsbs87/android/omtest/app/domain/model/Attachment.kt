package com.jsbs87.android.omtest.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Attachment(
    val assetId: String,
    val assetName: String,
    val name: String,
    val responseElementType: String,
    val value: String
) : Parcelable {
}