package com.myu.myumovie.data.model

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<DiscoverMovieResult>,
    val total_pages: Int,
    val total_results: Int
)