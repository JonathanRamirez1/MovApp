package com.jonathan.myapplication.data.network

import com.jonathan.myapplication.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    companion object {
        const val BASE_URL = "http://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    fun getDataFromApi(@Query("api_key")query: String?): Response<MoviesResponse>
}