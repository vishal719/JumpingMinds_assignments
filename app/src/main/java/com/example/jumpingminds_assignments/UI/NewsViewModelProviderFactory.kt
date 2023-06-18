package com.example.jumpingminds_assignments.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jumpingminds_assignments.repository.NewsRepository

@Suppress("UNCHECKED_CAST")
class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }

}