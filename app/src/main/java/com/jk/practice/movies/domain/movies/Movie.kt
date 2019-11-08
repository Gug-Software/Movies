package com.jk.practice.movies.domain.movies

import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.IMAGE_PATH

data class Movie(

    val id: Int,
    val originalTitle: String?,
    val backdropPath: String?,
    val releaseDate: String

) {

    val definitiveBackdropPath: String = getDefinitiveBackdropPath(backdropPath)

    private fun getDefinitiveBackdropPath(posterPath: String?): String {
        if (posterPath != null) {
            return "$IMAGE_PATH$posterPath"
        } else {
            return ""
        }

    }
}