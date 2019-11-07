package com.jk.practice.movies.ui.movies.adapter

import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse

class MovieItemListener(
    val clickListener: (movie: DtoTrendingResponse.DtoMovie) -> Unit
) {
    fun onClick(movie: DtoTrendingResponse.DtoMovie) = clickListener(movie)
}