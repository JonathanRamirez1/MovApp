package com.jonathan.myapplication.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonathan.myapplication.data.network.RetrofitService
import com.jonathan.myapplication.util.Constants.API_KEY
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: RetrofitService) :
    PagingSource<Int, Movies>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getDataFromApi(API_KEY, nextPageNumber)
            val responseData = mutableListOf<Movies>()
            val data = response.movieModels
            responseData.addAll(data)

            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextPageNumber.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        TODO("Not yet implemented")
    }
}