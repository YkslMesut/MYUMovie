package com.myu.myumovie.data.repository

import com.myu.myumovie.data.remote.MovieService
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
    private val api : MovieService,
    @Named("API_KEY") private val apiKey : String
) {
    suspend fun getDiscoverMovie() = api.getDiscoverMovie(apiKey)

    suspend fun getDiscoverTvShows() = api.getDiscoverTvShow(apiKey)
}