package com.jsbs87.android.omtest.app.data.entity

class ResponseEntity<T>(
    val response: T?,
    val error: ErrorResponse?
)

class ErrorResponse(
    val code: Int?,
    val message: String? = ""
)