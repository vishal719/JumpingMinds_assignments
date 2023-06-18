package com.example.jumpingminds_assignments.UI.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.NewsActivity
import com.example.jumpingminds_assignments.UI.NewsViewModel
import com.example.jumpingminds_assignments.adapters.NewsAdapter
import com.example.jumpingminds_assignments.databinding.FragmentSearchBinding
import com.example.jumpingminds_assignments.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {


    lateinit var binding : FragmentSearchBinding
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        var job: Job? = null
        binding.searchIndividual.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        binding.searchProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.searchProgressBar.visibility = View.VISIBLE
    }
}