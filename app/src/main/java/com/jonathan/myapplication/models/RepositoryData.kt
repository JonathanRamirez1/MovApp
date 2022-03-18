package com.jonathan.myapplication.models

import com.google.gson.annotations.SerializedName

data class RepositoryData(
    @SerializedName("poster_path") val images: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val description: String?)
