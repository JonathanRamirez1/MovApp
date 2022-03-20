package com.jonathan.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jonathan.myapplication.data.model.MoviesRepository
import com.jonathan.myapplication.data.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val api: RetrofitService) : ViewModel() {

    private var pagingSource: MoviesRepository? = null

    /**Si se necesita mas paginas se agregan aqui**/
    val flow = Pager(PagingConfig(pageSize = 0)) {
        MoviesRepository(api).also {
            pagingSource = it
        }
    }.flow
        .cachedIn(viewModelScope)

    //TODO USAR PARA REFRESCAR LOS DATOS
    fun clearData() = pagingSource?.invalidate()
}