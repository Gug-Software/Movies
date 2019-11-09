package com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail

import com.squareup.moshi.Json

data class DtoSpokenLanguage(

    @Json(name = "iso_639_1")
    val iso6391: String, // ja

    @Json(name = "name")
    val name: String // 日本語

)