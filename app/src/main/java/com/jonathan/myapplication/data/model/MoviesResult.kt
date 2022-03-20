package com.jonathan.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResult(
    val page: Int,
    @field:SerializedName("results")
    val movieModels: List<Movies>,
    @field:SerializedName("total_pages")
    val totalPages: Int,
    @field:SerializedName("total_results")
    val totalResults: Int
)