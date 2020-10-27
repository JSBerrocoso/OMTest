package com.jsbs87.android.omtest.app.domain.model

data class Recommentation(
    val contentType: String,
    val externalContentId: String,
    val id: Int,
    val images: List<Image>,
    val name: String,
    val prLevel: Int,
    val prName: String,
    val ratersCount: Int,
    val rating: Double,
    val type: String
): OMTestMovie