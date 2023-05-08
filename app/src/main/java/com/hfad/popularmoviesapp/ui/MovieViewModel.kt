package com.hfad.popularmoviesapp.ui

import androidx.lifecycle.*
import androidx.paging.*
import com.hfad.popularmoviesapp.data.moviedata.Movie
import com.hfad.popularmoviesapp.remote.MovieApi
import com.hfad.popularmoviesapp.ui.movie.MoviePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieApi: MovieApi): ViewModel() {

//    private val _movies = MutableLiveData<Movie>()
//    val movies: LiveData<Movie> = _movies

    val list = Pager(PagingConfig(pageSize = 2)) {
        MoviePaging(movieApi)
    }.liveData.cachedIn(viewModelScope)
}