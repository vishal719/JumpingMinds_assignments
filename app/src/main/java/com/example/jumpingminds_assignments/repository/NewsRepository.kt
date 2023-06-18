package com.example.jumpingminds_assignments.repository

import com.example.jumpingminds_assignments.api.RetrofitInstance
import com.example.jumpingminds_assignments.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews()

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery)

}