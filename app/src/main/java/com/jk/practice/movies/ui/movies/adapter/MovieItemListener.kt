package com.jk.practice.movies.ui.movies.adapter

import com.jk.practice.movies.domain.domain.movies.Movie

class MovieItemListener(
    val clickListener: (
        movie: Movie
    ) -> Unit
) {
    fun onClick(movie: Movie) = clickListener(movie)
}