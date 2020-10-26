package com.jsbs87.android.omtest.app.domain.model

import com.jsbs87.android.omtest.app.data.entity.AttachmentEntity
import com.jsbs87.android.omtest.app.data.entity.GenreEntity

class Film(
    val assetExternalId: String,
    val attachments: List<AttachmentEntity>,
    val contentProvider: String,
    val contentProviderExternalId: String,
    val definition: String,
    val description: String,
    val duration: Int,
    val externalId: String,
    val genreEntityList: List<GenreEntity>,
    val id: Int,
    val keywords: String,
    val name: String,
    val seriesNumberOfEpisodes: String,
    val status: Int,
    val type: String,
    val year: Int
) {
}