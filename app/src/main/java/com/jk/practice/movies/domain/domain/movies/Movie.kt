package com.jk.practice.movies.domain.domain.movies

import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.IMAGE_PATH
import com.jk.practice.movies.utils.DateUtils

data class Movie(

    val id: Int,
    val originalTitle: String?,
    val backdropPath: String?,
    val releaseDate: String

) {

    val definitiveBackdropPath: String = getDefinitiveBackdropPath(backdropPath)
    val releaseForHuman = DateUtils.convertDateForHuman(releaseDate)

    private fun getDefinitiveBackdropPath(posterPath: String?): String {
        if (posterPath != null) {
            return "$IMAGE_PATH$posterPath"
        } else {
            return ""
        }

    }
}