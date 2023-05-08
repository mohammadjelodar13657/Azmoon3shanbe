package com.hfad.popularmoviesapp.remote

import com.hfad.popularmoviesapp.data.moviedata.MovieResponse
import com.hfad.popularmoviesapp.data.searchdata.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    // https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}