package com.jonathan.myapplication.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.data.model.MovieRepository
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error


    fun getDetail(movieId: Long): LiveData<Movie> =
        repository.getMovie(movieId)
            .catch { _error.postValue(true) }
            .asLiveData()
}