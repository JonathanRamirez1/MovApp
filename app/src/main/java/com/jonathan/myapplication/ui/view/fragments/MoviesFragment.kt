package com.jonathan.myapplication.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.myapplication.R
import com.jonathan.myapplication.databinding.FragmentMoviesBinding
import com.jonathan.myapplication.ui.view.adapters.ClickListener
import com.jonathan.myapplication.ui.view.adapters.MoviesAdapter
import com.jonathan.myapplication.ui.viewmodel.MoviesViewModel
import com.jonathan.myapplication.util.Constants.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeAdapter()
        getData()
    }

    private fun setHomeAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewMovies.layoutManager = layoutManager
        moviesAdapter = MoviesAdapter(this)
        binding.recyclerviewMovies.setHasFixedSize(true)
        binding.recyclerviewMovies.adapter = moviesAdapter
    }

    /**Obtiene los datos desde la pagina**/
    private fun getData() {
        lifecycleScope.launch {
            moviesViewModel.flow.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }
    }

    override fun clicked(value: Long?) {
        val movieId = Bundle()
        movieId.putLong(MOVIE_ID, value ?: 0)

        findNavController().navigate(R.id.action_moviesFragment_to_movieFragment, movieId)
    }
}