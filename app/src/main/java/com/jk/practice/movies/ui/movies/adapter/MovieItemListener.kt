package com.jk.practice.movies.ui.movies.adapter

import android.view.View
import android.widget.ImageView
import com.jk.practice.movies.domain.domain.movies.Movie

class MovieItemListener(

    val clickListener: (
        movie: Movie, view: View
    ) -> Unit

) {
    fun onClick(
        movie: Movie,
        view: View
    ) = clickListener(movie, view)
}