package com.jsbs87.android.omtest.app.domain.model

import android.os.Parcelable
import com.google.gson.Gson
import com.jsbs87.android.omtest.app.data.entity.GenreEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Movie(
    val assetExternalId: String,
    val attachments: List<Attachment>,
    val contentProvider: String,
    val contentProviderExternalId: String,
    val definition: String,
    val description: String,
    val duration: Int,
    val externalId: String,
    val id: Int,
    val keywords: String,
    val name: String,
    val seriesNumberOfEpisodes: String,
    val status: Int,
    val type: String,
    val year: Int,
    val shortName: String,
    var favorite: Boolean = false,
    var showTitle: Boolean = false
) : Parcelable, OMTestMovie {
    fun toJson(): String? {
        return Gson().toJson(this)
    }

    companion object {
        fun empty() = Movie(
            "", emptyList(), "", "", "", "", 0, "", 0, "", "", "", 0, "", 0, "", false, false
        )
    }
}