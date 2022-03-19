package com.jonathan.myapplication.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.jonathan.myapplication.R
import com.jonathan.myapplication.databinding.FragmentMovieBinding
import com.jonathan.myapplication.ui.viewmodel.MovieViewModel
import com.jonathan.myapplication.util.Constants.IMAGE_URL
import com.jonathan.myapplication.util.Constants.MOVIE_ID

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getLong(MOVIE_ID) ?: 0

        movieViewModel.getDetail(movieId).observe(viewLifecycleOwner, { movie ->
            /*Glide.with(this).load(IMAGE_URL + movie.backdrop_path)
                .into()*/

            binding.materialTextViewHola.text = movie.backdrop_path
        })
    }
}