package com.jonathan.myapplication.data.repository

import androidx.paging.PagingSource
import com.jonathan.myapplication.data.model.Movies
import com.jonathan.myapplication.data.network.RetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryTest {

    @RelaxedMockK
    private lateinit var retrofitService: RetrofitService

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRepository = MoviesRepository(retrofitService)
    }

    @Test
    fun load(params: PagingSource.LoadParams<Int>): Unit = runBlocking {
        val nextPageNumber = params.key ?: 1
        val response = retrofitService.getDataFromApi("API_KEY_TEST", nextPageNumber)
        val responseData = mutableListOf<Movies>()
        val data = response.movieModels
        val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1

        coEvery {
            responseData.addAll(data)
        } returns true

        verify {
            PagingSource.LoadResult.Page(
                responseData,
                prevKey,
                nextPageNumber.plus(1)
            )
        }
    }
}