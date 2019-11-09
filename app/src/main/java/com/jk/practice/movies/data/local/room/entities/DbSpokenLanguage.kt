package com.jk.practice.movies.data.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "iso6391"])
data class DbSpokenLanguage(
    var movieId: Int,
    val iso6391: String, // ja
    val name: String // 日本語
)

