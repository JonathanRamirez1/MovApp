package com.jonathan.myapplication.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jonathan.myapplication.data.model.Movies
import com.jonathan.myapplication.databinding.ItemMoviesBinding
import com.jonathan.myapplication.util.Constants.IMAGE_URL
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(private val listener: ClickListener) :
    PagingDataAdapter<Movies, MoviesAdapter.HomeViewHolder>(MovieDiff) {

    private lateinit var recyclerView: RecyclerView

    private var items: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.clicked(getItem(position)?.id)
        }

        Glide.with(holder.itemView).load(IMAGE_URL + getItem(position)?.posterPath)
            .into(holder.itemView.imageViewMovie)
        holder.itemView.materialTextViewTitle.text = getItem(position)?.originalTitle
        holder.itemView.materialTextViewOverview.text = getItem(position)?.overview
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    class HomeViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    object MovieDiff : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean =
            newItem == oldItem
    }
}

interface ClickListener {
    fun clicked(value: Long?)
}