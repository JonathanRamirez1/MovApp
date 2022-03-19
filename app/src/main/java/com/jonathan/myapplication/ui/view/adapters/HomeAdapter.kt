package com.jonathan.myapplication.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.myapplication.data.model.Movies
import com.jonathan.myapplication.databinding.ItemMoviesBinding

class HomeAdapter(private val recyclerViewHome: RecyclerViewHomeClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private lateinit var recyclerView: RecyclerView

    var items: List<Movies> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items[position]
        item.let {
            holder.apply {
                bind(item, isLinearLayoutManager())
                itemView.tag = item
            }
        }

        holder.itemView.setOnClickListener {
            recyclerViewHome.clickOnItem(
                item,
                holder.itemView
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList: List<Movies>) {
        items = itemList
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager

    class HomeViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movies, isLinearLayoutManager: Boolean) {
            binding.apply {
                doc = item
                executePendingBindings()
            }
        }
    }
}

interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: Movies, card: View)
}