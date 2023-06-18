package com.example.jumpingminds_assignments.repository

import com.example.jumpingminds_assignments.api.RetrofitInstance
import com.example.jumpingminds_assignments.db.ArticleDatabase
import com.example.jumpingminds_assignments.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews() =
        RetrofitInstance.api.getNews()

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()


}