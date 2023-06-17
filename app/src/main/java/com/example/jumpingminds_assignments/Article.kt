package com.example.jumpingminds_assignments

import androidx.room.Entity


@Entity(tableName = "articles")
data class Article(
    val events: List<Any>,
    val featured: Boolean,
    val id: Int,
    val image_url: String,
    val launches: List<Any>,
    val news_site: String,
    val published_at: String,
    val summary: String,
    val title: String,
    val updated_at: String,
    val url: String
)