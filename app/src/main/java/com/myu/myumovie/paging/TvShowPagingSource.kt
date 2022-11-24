package com.myu.myumovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myu.myumovie.data.model.DiscoverTvShowsResult
import com.myu.myumovie.data.remote.MovieService
import javax.inject.Inject
import javax.inject.Named

class TvShowPagingSource (
    private val apiService : MovieService,
    @Named("API_KEY") private val apiKey : String
) : PagingSource<Int,DiscoverTvShowsResult>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverTvShowsResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverTvShowsResult> {
      return try {
          val currentPage = params.key ?:1
          val response = apiService.getDiscoverTvShow(apiKey)
          val responseData = mutableListOf<DiscoverTvShowsResult>()
          val data = response.body()?.results ?: emptyList()
          responseData.addAll(data)

          LoadResult.Page(
              data = responseData,
              prevKey = if (currentPage == 1) null else -1,
              nextKey = currentPage.plus(1)
          )
      } catch (e : java.lang.Exception) {
          LoadResult.Error(e)
      }
    }

}