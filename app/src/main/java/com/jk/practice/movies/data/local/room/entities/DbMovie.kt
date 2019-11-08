package com.jk.practice.movies.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbMovie(

    @PrimaryKey
    val id: Int, // 299534

    val adult: Boolean, // false
    val backdropPath: String?, // /3uG3aOhEzFCjcQulsJQiAzLSrw8.jpg
    val firstAirDate: String?, // 2010-10-31
    val mediaType: String, // movie
    val name: String?, // The Walking Dead
    val originalLanguage: String, // en
    val originalName: String?, // The Walking Dead
    val originalTitle: String, // 47 Meters Down: Uncaged
    val overview: String, // A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean's deadliest shark species.
    val popularity: Double, // 97.537
    val posterPath: String?, // /g4z7mDmJmx23vsVg6XNWcnXb6gc.jpg
    val releaseDate: String, // 2019-08-15
    val title: String, // 47 Meters Down: Uncaged
    val video: Boolean, // false
    val voteAverage: Double, // 5.1
    val voteCount: Int // 164

)