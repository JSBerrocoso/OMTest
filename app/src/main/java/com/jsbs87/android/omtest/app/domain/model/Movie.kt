package com.jsbs87.android.omtest.app.domain.model

import android.os.Parcelable
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
    val shortName: String
) : Parcelable, OMTestMovie{

    companion object {
        fun empty() = Movie(
            "", emptyList(), "", "", "", "", 0, "",  0, "", "", "", 0, "", 0, ""
        )
    }
}