package com.jonathan.myapplication.ui.view.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val poster = "https://image.tmdb.org/t/p/w500$imageUrl"
        Glide.with(view.context)
            .load(poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("userRatingFormat")
fun bindRating(view: TextView, rating: Double?) {
    rating?.let {
        view.text = rating.toString()
    }
}