package com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail

import com.squareup.moshi.Json

data class DtoGenre(

    @Json(name = "id")
    val id: Int, // 28

    @Json(name = "name")
    val name: String // Action

)