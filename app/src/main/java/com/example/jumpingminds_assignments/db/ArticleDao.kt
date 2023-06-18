package com.example.jumpingminds_assignments.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jumpingminds_assignments.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

}