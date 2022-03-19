package com.jonathan.myapplication.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.myapplication.R
import com.jonathan.myapplication.data.model.Movie
import com.jonathan.myapplication.databinding.ActivityHomeBinding
import com.jonathan.myapplication.ui.view.adapters.HomeAdapter
import com.jonathan.myapplication.ui.view.adapters.RecyclerViewHomeClickListener
import com.jonathan.myapplication.ui.viewmodel.HomeViewModel
import com.jonathan.myapplication.util.Resource
import com.jonathan.myapplication.util.contentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RecyclerViewHomeClickListener {

    private val binding: ActivityHomeBinding by contentView(R.layout.activity_home)
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            recyclerviewMovies.apply {
                setHomeAdapter()
            }
        }

        homeViewModel.getPopular(resources.getString(R.string.api_key))
        setObservers()
    }

    private fun setHomeAdapter() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewMovies.layoutManager = layoutManager
        homeAdapter = HomeAdapter(this@HomeActivity)
        binding.recyclerviewMovies.setHasFixedSize(true)
        binding.recyclerviewMovies.adapter = homeAdapter
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