package com.jonathan.myapplication.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.jonathan.myapplication.R
import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.databinding.ActivityHomeBinding
import com.jonathan.myapplication.ui.view.adapters.HomeAdapter
import com.jonathan.myapplication.ui.view.adapters.RecyclerViewHomeClickListener
import com.jonathan.myapplication.ui.viewmodel.HomeViewModel
import com.jonathan.myapplication.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RecyclerViewHomeClickListener {

    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter( this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            recyclerviewMovies.apply {
                adapter = homeAdapter
            }
        }

        homeViewModel.getPopular(resources.getString(R.string.api_key))
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.moviePopular.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data!!.movies
                    homeAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun clickOnItem(data: Movie, card: View) {
        TODO("Not yet implemented")
    }
}