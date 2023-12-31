package com.example.jumpingminds_assignments.UI.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.NewsActivity
import com.example.jumpingminds_assignments.UI.NewsDetailsActivity
import com.example.jumpingminds_assignments.UI.NewsViewModel
import com.example.jumpingminds_assignments.adapters.NewsAdapter
import com.example.jumpingminds_assignments.databinding.FragmentNewsBinding
import com.example.jumpingminds_assignments.models.Article
import com.example.jumpingminds_assignments.util.Resource


class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding: FragmentNewsBinding

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        newsAdapter.setOnItemClickListener {
            val intent: Intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra("news",it)
            startActivity(intent)
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.results.toList())
                        cacheData(newsResponse.results.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()

                    // Inflating cached items
                    viewModel.getCachedNews().observe(viewLifecycleOwner, Observer { articles ->
                        newsAdapter.differ.submitList(articles)
                    })

                    response.message?.let { message ->
                        Toast.makeText(activity, "No Internet", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    fun cacheData(list:List<Article>?){
        list?.forEach { item ->
            viewModel.cacheArticle(item)
        }
    }
}