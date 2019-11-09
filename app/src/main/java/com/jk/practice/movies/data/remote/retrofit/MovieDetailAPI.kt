package com.jk.practice.movies.data.remote.retrofit

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailAPI {

    /**
     * Get the primary information about a movie.
     * https://developers.themoviedb.org/3/movies/get-movie-details
     *
     * @param id movieId
     * @param apiKey Clave de la API (v3 auth)
     * @param language Specify a language to query translatable fields with.
     */
    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int, @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Deferred<DtoMovieDetail>

}