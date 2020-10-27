package com.jsbs87.android.omtest.app.data.entity

data class AvailabilityEntity(
    val categories: List<CategoryEntity>,
    val endTime: Long,
    val images: List<ImageEntity>,
    val serviceId: String,
    val startTime: Long,
    val videoId: String
)