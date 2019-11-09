package com.jk.practice.movies.data.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "id"])
data class DbGenre(
    var movieId: Int,
    val id: Int, // 28
    val name: String // Action
)



