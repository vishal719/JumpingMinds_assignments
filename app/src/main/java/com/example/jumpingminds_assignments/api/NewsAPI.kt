package com.example.jumpingminds_assignments.api

import com.example.jumpingminds_assignments.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v4/articles/")
    suspend fun getNews(): Response<NewsResponse>

    @GET("v2/articles/")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String
    ): Response<NewsResponse>

}