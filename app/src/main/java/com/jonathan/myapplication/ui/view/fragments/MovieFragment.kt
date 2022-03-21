package com.jonathan.myapplication.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jonathan.myapplication.databinding.FragmentMovieBinding
import com.jonathan.myapplication.ui.viewmodel.MovieViewModel
import com.jonathan.myapplication.util.Constants.IMAGE_URL
import com.jonathan.myapplication.util.Constants.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        val backdrop = binding.imageViewBackdropPath
        val movieId = arguments?.getLong(MOVIE_ID) ?: 0

        if (movieId == 0L)
            errorOperation()

        movieViewModel.getDetail(movieId).observe(viewLifecycleOwner, { movie ->

            Glide.with(this).load(IMAGE_URL + movie.backdrop_path).into(backdrop)
            binding.materialTextViewMovieTitle.text = movie.title
            binding.materialTextViewOverview.text = movie.overview
            binding.materialTextViewVoteCount.text = movie.vote_count
            binding.materialTextViewDate.text = movie.release_date
            binding.materialTextViewPopularity.text = movie.popularity
        })

        movieViewModel.error.observe(viewLifecycleOwner, { isError ->
            if (isError) {
                errorOperation()
            }
        })
    }

    private fun errorOperation() {
        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
        activity?.onBackPressed()
    }
}