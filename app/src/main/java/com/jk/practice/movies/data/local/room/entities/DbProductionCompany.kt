package com.jk.practice.movies.data.local.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "id"])
data class DbProductionCompany(
    var movieId: Int,
    val id: Int, // 420
    val logoPath: String?, // /hUzeosd33nzE5MCNsZxCGEKTXaQ.png
    val name: String, // Marvel Studios
    val originCountry: String // US
)



