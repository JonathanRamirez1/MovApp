package com.jonathan.myapplication.data.model

import com.jonathan.myapplication.data.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val retrofitService: RetrofitService
) {
    suspend fun getPopular(apikey: String): Response<MoviesResponse> = withContext(
        Dispatchers.IO) {
        val popular = retrofitService.getDataFromApi(apikey)
        popular
    }
}