package com.example.jumpingminds_assignments.api

import com.example.jumpingminds_assignments.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v4/articles/")
    suspend fun getNews(): Response<NewsResponse>

    @GET("v4/articles")
    suspend fun searchForNews(
        @Query("news_site")
        searchQuery: String
    ): Response<NewsResponse>

}