package com.example.jumpingminds_assignments.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.fragments.NewsFragment
import com.example.jumpingminds_assignments.UI.fragments.SavedFragment
import com.example.jumpingminds_assignments.UI.fragments.SearchFragment
import com.example.jumpingminds_assignments.databinding.ActivityNewsBinding
import com.example.jumpingminds_assignments.db.ArticleDatabase
import com.example.jumpingminds_assignments.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var binding : ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()

            when (item.itemId) {
                R.id.breakingNewsFragment -> transaction.replace(R.id.main_fragment, NewsFragment())
                R.id.savedNewsFragment -> transaction.replace(R.id.main_fragment, SavedFragment())
                R.id.searchNewsFragment -> transaction.replace(R.id.main_fragment, SearchFragment())
            }

            transaction.commit()

            true
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, NewsFragment()).commit()

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    }
}