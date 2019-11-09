package com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail

import com.squareup.moshi.Json

data class DtoMovieDetail(

    @Json(name = "adult")
    val adult: Boolean, // false

    @Json(name = "backdrop_path")
    val backdropPath: String?, // /7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg

    @Json(name = "belongs_to_collection")
    val dtoBelongsToCollection: DtoBelongsToCollection?,

    @Json(name = "budget")
    val budget: Int, // 356000000

    @Json(name = "genres")
    val dtoGenres: List<DtoGenre>,

    @Json(name = "homepage")
    val homepage: String?, // https://www.marvel.com/movies/avengers-endgame

    @Json(name = "id")
    val id: Int, // 299534

    @Json(name = "imdb_id")
    val imdbId: String, // tt4154796

    @Json(name = "original_language")
    val originalLanguage: String, // en

    @Json(name = "original_title")
    val originalTitle: String, // Avengers: Endgame

    @Json(name = "overview")
    val overview: String, // After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.

    @Json(name = "popularity")
    val popularity: Double, // 93.133

    @Json(name = "poster_path")
    val posterPath: String, // /or06FN3Dka5tukK1e9sl16pB3iy.jpg

    @Json(name = "production_companies")
    val dtoProductionCompanies: List<DtoProductionCompany>,

    @Json(name = "production_countries")
    val dtoProductionCountries: List<DtoProductionCountry>,

    @Json(name = "release_date")
    val releaseDate: String, // 2019-04-24

    @Json(name = "revenue")
    val revenue: Long, // 2764881856

    @Json(name = "runtime")
    val runtime: Int?, // 181

    @Json(name = "spoken_languages")
    val dtoSpokenLanguages: List<DtoSpokenLanguage>,

    @Json(name = "status")
    val status: String, // Released

    @Json(name = "tagline")
    val tagline: String, // Part of the journey is the end.

    @Json(name = "title")
    val title: String, // Avengers: Endgame

    @Json(name = "video")
    val video: Boolean, // false

    @Json(name = "vote_average")
    val voteAverage: Double, // 8.4

    @Json(name = "vote_count")
    val voteCount: Int // 7421

)