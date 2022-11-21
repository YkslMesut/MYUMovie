package com.myu.myumovie.data.remote

import com.myu.myumovie.data.model.DiscoverMovieResponse
import com.myu.myumovie.data.model.DiscoverTvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key") api_key : String
    ) : Response<DiscoverMovieResponse>

    @GET("discover/tv")
    suspend fun getDiscoverTvShow(
        @Query("api_key") api_key : String
    ) : Response<DiscoverTvShowResponse>
}