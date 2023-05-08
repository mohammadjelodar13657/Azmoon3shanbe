package com.hfad.popularmoviesapp.ui.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.hfad.popularmoviesapp.R
import com.hfad.popularmoviesapp.databinding.FragmentMovieBinding
import com.hfad.popularmoviesapp.ui.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MoviePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel.list.observe(viewLifecycleOwner) {
            Log.i(TAG, "onViewCreated: ${it.map { it.title }}")
        }

        movieAdapter = MoviePagingAdapter()
        movieViewModel.list.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it.map { it })
        }
    }

    fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}