package com.jonathan.myapplication.data.repository

import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.data.network.RetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    @RelaxedMockK
    private lateinit var retrofitService: RetrofitService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getMovie(): Unit = runBlocking {
        val movie = Movie(
            "backdrop_path",
            "title",
            "overview",
            "vote_count",
            "release_date",
            "popularity")

        coEvery {
            retrofitService.getDataFromDatabase(2022, "API_KEY_TEST")
        } returns movie
    }
}
