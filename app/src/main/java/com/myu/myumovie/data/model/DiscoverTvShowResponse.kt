package com.myu.myumovie.data.model

data class DiscoverTvShowResponse(
    val page: Int,
    val results: List<DiscoverTvShowsResult>,
    val total_pages: Int,
    val total_results: Int
)