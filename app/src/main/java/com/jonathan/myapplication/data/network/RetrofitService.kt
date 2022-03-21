package com.jonathan.myapplication.data.network

import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.data.model.MoviesResult
import com.jonathan.myapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    companion object {
        const val BASE_URL = "http://api.themoviedb.org/3/"
    }

    @GET("movie/popular")
    suspend fun getDataFromApi(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MoviesResult

    @GET("movie/{movie_id}")
    suspend fun getDataFromDatabase(
        @Path("movie_id") movieId: Long,
        @Query("api_key") query: String?
    ): Movie
}