package com.jsbs87.android.omtest.app.data.entity

import com.jsbs87.android.omtest.app.domain.model.Movie

data class MovieEntity(
    val assetExternalId: String?,
    val attachments: List<AttachmentEntity>?,
    val contentProvider: String?,
    val contentProviderExternalId: String?,
    val definition: String?,
    val description: String?,
    val duration: Int?,
    val externalId: String?,
    val genreEntityList: List<GenreEntity>?,
    val id: Int?,
    val keywords: String?,
    val name: String?,
    val shortName: String?,
    val seriesNumberOfEpisodes: String?,
    val status: Int?,
    val type: String?,
    val year: Int?
) {
    fun toMovie(): Movie {
        return Movie(
            assetExternalId ?: "",
            attachments?.map { it.toAttachment()}?: emptyList(),
            contentProvider ?: "",
            contentProviderExternalId ?: "",
            definition ?: "",
            description ?: "",
            duration ?: 0,
            externalId ?: "",
            genreEntityList ?: emptyList(),
            id ?: 0,
            keywords ?: "",
            name ?: "",
            seriesNumberOfEpisodes ?: "",
            status ?: 0,
            type ?: "",
            year ?: 0,
            shortName ?: "",
        )
    }
}