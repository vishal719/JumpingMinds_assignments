package com.example.jumpingminds_assignments.models

import com.example.jumpingminds_assignments.models.Article

data class NewsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Article>
)