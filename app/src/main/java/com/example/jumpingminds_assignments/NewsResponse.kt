package com.example.jumpingminds_assignments

data class NewsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)