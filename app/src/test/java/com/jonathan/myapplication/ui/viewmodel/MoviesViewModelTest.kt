package com.jonathan.myapplication.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.jonathan.myapplication.data.model.Movies
import com.jonathan.myapplication.data.network.RetrofitService
import com.jonathan.myapplication.data.repository.MoviesRepository
import com.jonathan.myapplication.ui.view.adapters.MoviesAdapter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @RelaxedMockK
    private lateinit var retrofitService: RetrofitService

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    @RelaxedMockK
    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    @RelaxedMockK
    private lateinit var flow: Flow<PagingData<Movies>>

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(retrofitService)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFlow() = runTest {
        coEvery {
            flow.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }
    }

    @Test
    fun clearData() {
        every { moviesRepository.invalidate() }
    }
}