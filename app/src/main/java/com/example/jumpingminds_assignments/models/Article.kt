package com.example.jumpingminds_assignments.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
    val featured: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image_url: String,
    val news_site: String,
    val published_at: String,
    val summary: String,
    val title: String,
    val updated_at: String,
    val url: String
)