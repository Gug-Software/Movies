package com.jk.practice.movies.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbMovie(

    @PrimaryKey
    val id: Int, // 299534

    val backdropPath: String?, // /3uG3aOhEzFCjcQulsJQiAzLSrw8.jpg
    val originalTitle: String, // 47 Meters Down: Uncaged
    val originalLanguage: String, // en
    val overview: String, // A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean's deadliest shark species.
    val popularity: Double, // 97.537
    val releaseDate: String, // 2019-08-15
    val voteAverage: Double, // 5.1
    val voteCount: Int, // 164
    val budget: Int // 356000000


)