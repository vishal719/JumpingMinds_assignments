package com.example.jumpingminds_assignments.UI.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.NewsActivity
import com.example.jumpingminds_assignments.UI.NewsDetailsActivity
import com.example.jumpingminds_assignments.UI.NewsViewModel
import com.example.jumpingminds_assignments.adapters.NewsAdapter
import com.example.jumpingminds_assignments.databinding.FragmentNewsBinding
import com.example.jumpingminds_assignments.databinding.FragmentSavedBinding
import com.example.jumpingminds_assignments.util.Resource

class SavedFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
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

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
            newsAdapter.differ.submitList(articles)
        })
    }

}