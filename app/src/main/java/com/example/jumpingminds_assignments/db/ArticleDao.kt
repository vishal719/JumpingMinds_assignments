package com.example.jumpingminds_assignments.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jumpingminds_assignments.models.Article
import com.example.jumpingminds_assignments.models.ArticleCache

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

}

@Dao
interface CachedArticleDao {

    @Insert(entity = ArticleCache::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun uppsert(article: Article): Long

    @Query("SELECT * FROM cache")
    fun getCachedArticles(): LiveData<List<Article>>

}
