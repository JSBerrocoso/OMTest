package com.jsbs87.android.omtest.app.domain.model

data class Availability(
    val categories: List<Category>,
    val endTime: Long,
    val images: List<Image>,
    val serviceId: String,
    val startTime: Long,
    val videoId: String
)