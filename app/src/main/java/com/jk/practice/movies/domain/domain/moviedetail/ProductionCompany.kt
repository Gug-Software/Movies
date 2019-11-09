package com.jk.practice.movies.domain.domain.moviedetail

import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.IMAGE_PATH

data class ProductionCompany(

    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String

) {

    val definitiveLgoPath: String = getDefinitivePosterPath(logoPath)

    private fun getDefinitivePosterPath(logoPath: String?): String {

        if (logoPath != null) {
            return "$IMAGE_PATH$logoPath"
        }

        return ""

    }

}