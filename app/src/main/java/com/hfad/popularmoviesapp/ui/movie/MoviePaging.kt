package com.hfad.popularmoviesapp.ui.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hfad.popularmoviesapp.data.moviedata.Movie
import com.hfad.popularmoviesapp.remote.MovieApi
import com.hfad.popularmoviesapp.util.Constants

class MoviePaging (private val movieApi: MovieApi): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Movie> {
        val page = params.key?:1

        return try {

            val data = movieApi.getPopularMovies(Constants.API_KEY, Constants.LANGUAGE, page)

            PagingSource.LoadResult.Page(
                data = data.body()?.movies!!,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(data.body()?.movies!!.isEmpty()!!) null else page + 1
            )
        } catch (e:Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}