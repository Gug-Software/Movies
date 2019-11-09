package com.jk.practice.movies.domain.domain.moviedetail

import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.IMAGE_PATH
import gug.co.com.moviemarket.domain.details.Genre

data class MovieDetail(

    val id: Int,
    val originalTitle: String = "",
    val backdropPath: String? = "",
    val voteAverage: Double = 0.0,
    val overview: String,
    val releaseDate: String,
    val budget: Int, // 356000000
    val originalLanguage: String, // en
    val popularity: Double, // 97.537
    val voteCount: Int, // 164
    var genres: List<Genre>,
    var companies: List<ProductionCompany>,
    var languages: List<SpokenLanguage>

) {

    val definitiveBackdropPath: String = getDefinitivePosterPath(backdropPath)
    val average = voteAverage.toString()

    private fun getDefinitivePosterPath(posterPath: String?): String {
        if (posterPath != null) {
            return "$IMAGE_PATH$posterPath"
        } else {
            return ""
        }
    }

}