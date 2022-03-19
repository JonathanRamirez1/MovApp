package com.jonathan.myapplication.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.myapplication.util.Resource
import com.jonathan.myapplication.data.model.HomeRepository
import com.jonathan.myapplication.data.model.MoviesResponse
import com.jonathan.myapplication.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val moviePopular: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun getPopular(apikey: String) {
        moviePopular.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = homeRepository.getPopular(apikey)
                    moviePopular.postValue(Resource.Success(response.body()!!))
                } else
                    moviePopular.postValue(Resource.Error("No Internet Connection"))
            } catch (ex: Exception) {
                when (ex) {
                    is IOException -> moviePopular.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> moviePopular.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }
}