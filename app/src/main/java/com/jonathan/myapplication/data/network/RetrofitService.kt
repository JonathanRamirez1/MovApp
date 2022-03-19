package com.jonathan.myapplication.data.network

import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    companion object {
        const val BASE_URL = "http://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    suspend fun getDataFromApi(@Query("api_key") query: String?): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getDataFromDatabase(
        @Path("movie_id") movieId: Long,
        @Query("api_key") query: String?
    ): Movie
}