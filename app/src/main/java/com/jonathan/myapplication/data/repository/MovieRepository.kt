package com.jonathan.myapplication.data.repository

import com.jonathan.myapplication.data.network.RetrofitService
import com.jonathan.myapplication.util.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: RetrofitService) {

    fun getMovie(movieId: Long) = flow {

        emit(api.getDataFromDatabase(movieId, API_KEY))

    }.flowOn(Dispatchers.IO)
}