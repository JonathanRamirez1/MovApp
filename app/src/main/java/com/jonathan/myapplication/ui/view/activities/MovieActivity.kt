package com.jonathan.myapplication.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jonathan.myapplication.databinding.ActivityMovieBinding
import com.jonathan.myapplication.ui.viewmodel.MovieViewModel
import com.jonathan.myapplication.util.Constants.MOVIE_ID
import android.util.Log
import com.jonathan.myapplication.R
import com.jonathan.myapplication.util.contentView


class MovieActivity : AppCompatActivity() {

    private val binding: ActivityMovieBinding by contentView(R.layout.activity_movie)

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.getStringExtra(MOVIE_ID)

        Log.e("Hola", "Aqui esta: $movieId") //TODO VERIFICAR ID QUE ESTA LLEGANDO VACIO

        movieId?.toLongOrNull()?.let {
            movieViewModel.getDetail(it).observe(this, { movie ->
                /*Glide.with(this).load(IMAGE_URL + movie.backdrop_path)
                        .into()*/

                binding.materialTextViewHola.text = movie.backdrop_path
            })
        }
    }
}