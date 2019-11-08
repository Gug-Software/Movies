package com.jk.practice.movies.data.remote.retrofit.dtos.movies


import com.squareup.moshi.Json

data class DtoTrendingResponse(
    @Json(name = "page")
    val page: Int, // 1
    @Json(name = "results")
    val dtoMovies: List<DtoMovie>,
    @Json(name = "total_pages")
    val totalPages: Int, // 1000
    @Json(name = "total_results")
    val totalResults: Int // 20000
) {
    data class DtoMovie(
        @Json(name = "adult")
        val adult: Boolean, // false
        @Json(name = "backdrop_path")
        val backdropPath: String?, // /3uG3aOhEzFCjcQulsJQiAzLSrw8.jpg
        @Json(name = "first_air_date")
        val firstAirDate: String?, // 2010-10-31
        @Json(name = "genre_ids")
        val genreIds: List<Int>,
        @Json(name = "id")
        val id: Int, // 480105
        @Json(name = "media_type")
        val mediaType: String, // movie
        @Json(name = "name")
        val name: String?, // The Walking Dead
        @Json(name = "origin_country")
        val originCountry: List<String>?,
        @Json(name = "original_language")
        val originalLanguage: String, // en
        @Json(name = "original_name")
        val originalName: String?, // The Walking Dead
        @Json(name = "original_title")
        val originalTitle: String, // 47 Meters Down: Uncaged
        @Json(name = "overview")
        val overview: String, // A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean's deadliest shark species.
        @Json(name = "popularity")
        val popularity: Double, // 97.537
        @Json(name = "poster_path")
        val posterPath: String?, // /g4z7mDmJmx23vsVg6XNWcnXb6gc.jpg
        @Json(name = "release_date")
        val releaseDate: String, // 2019-08-15
        @Json(name = "title")
        val title: String, // 47 Meters Down: Uncaged
        @Json(name = "video")
        val video: Boolean, // false
        @Json(name = "vote_average")
        val voteAverage: Double, // 5.1
        @Json(name = "vote_count")
        val voteCount: Int // 164
    )
}