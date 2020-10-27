package com.jsbs87.android.omtest.app.data.entity

import com.jsbs87.android.omtest.app.domain.model.Recommentation

data class RecommentationEntity(
    val contentType: String,
    val externalContentId: String,
    val id: Int,
    val images: List<ImageEntity>,
    val name: String,
    val prLevel: Int,
    val prName: String,
    val ratersCount: Int,
    val rating: Double,
    val type: String
) {
    fun toRecommendation(): Recommentation {
        return Recommentation(
            contentType,
            externalContentId,
            id,
            images.map { it.toImage()},
            name,
            prLevel,
            prName,
            ratersCount,
            rating,
            type
        )
    }
}