package com.hfad.popularmoviesapp.data.moviedata

import com.hfad.popularmoviesapp.data.moviedata.Movie

data class MovieResponse(
    val page: Int,
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)