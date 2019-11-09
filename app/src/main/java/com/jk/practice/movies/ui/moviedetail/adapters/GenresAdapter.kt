package com.jk.practice.movies.ui.moviedetail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jk.practice.movies.databinding.RecyclerItemGenreBinding
import gug.co.com.moviemarket.domain.details.Genre

class GenresAdapter() :
    ListAdapter<Genre, GenresAdapter.GenreViewHolder>(GenreDiffCallback()) {

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder.from(
            parent
        )
    }

    class GenreViewHolder private constructor(
        val binding: RecyclerItemGenreBinding
    ) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Genre) {
            binding.genre = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GenreViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemGenreBinding.inflate(layoutInflater, parent, false)

                return GenreViewHolder(binding)
            }
        }
    }

}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class GenreDiffCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}
