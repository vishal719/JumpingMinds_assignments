package com.example.jumpingminds_assignments.repository

import com.example.jumpingminds_assignments.api.RetrofitInstance
import com.example.jumpingminds_assignments.db.ArticleDatabase
import com.example.jumpingminds_assignments.models.Article

class NewsRepository(val db: ArticleDatabase) {


    // Api calls
    suspend fun getBreakingNews() = RetrofitInstance.api.getNews()
    suspend fun searchNews(searchQuery: String) = RetrofitInstance.api.searchForNews(searchQuery)

    //Room database calls
    suspend fun cacheInsert(article: Article) = db.getCachedArticlesDao().uppsert(article)
    fun getCachedNews() = db.getCachedArticlesDao().getCachedArticles()

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)
    fun getSavedNews() = db.getArticleDao().getAllArticles()


}