package com.example.jumpingminds_assignments.UI.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jumpingminds_assignments.R
import com.example.jumpingminds_assignments.UI.NewsActivity
import com.example.jumpingminds_assignments.UI.NewsViewModel

class SavedFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

    }

}