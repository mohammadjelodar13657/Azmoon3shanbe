package com.hfad.popularmoviesapp.data.searchdata

import com.hfad.popularmoviesapp.data.moviedata.Movie

data class SearchResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)