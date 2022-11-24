package com.myu.myumovie.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.myu.myumovie.data.model.DiscoverMovieResponse
import com.myu.myumovie.data.model.DiscoverTvShowResponse
import com.myu.myumovie.data.remote.MovieService
import com.myu.myumovie.data.repository.MovieRepository
import com.myu.myumovie.paging.TvShowPagingSource
import kotlinx.coroutines.launch
import javax.inject.Named

class FeedMoviesViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    private val apiService : MovieService,
    @Named("API_KEY") private val apiKey : String
): ViewModel() {
    private val TAG = "FeedMoviesViewModel"

    private val _responseMovie = MutableLiveData<DiscoverMovieResponse>()
    val discoverMovieResponse : LiveData<DiscoverMovieResponse>
    get() = _responseMovie

    private val _responseTvShows = MutableLiveData<DiscoverTvShowResponse>()
    val discoverTvShowsResponse : LiveData<DiscoverTvShowResponse>
        get() = _responseTvShows



    init {
        getDiscoverTvShows()
        getDiscoverMovies()
    }

    private fun getDiscoverMovies() = viewModelScope.launch {
        repository.getDiscoverMovie().let { response ->
        if (response.isSuccessful) {
            _responseMovie.postValue(response.body())
        }
        }
    }
    private fun getDiscoverTvShows() = viewModelScope.launch {
        repository.getDiscoverTvShows().let { response ->
            if (response.isSuccessful) {
                _responseTvShows.postValue(response.body())
            }
        }
    }
}