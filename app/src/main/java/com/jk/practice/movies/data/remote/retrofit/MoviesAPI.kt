package com.jk.practice.movies.data.remote.retrofit

import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesAPI {

    companion object {
        const val MEDIA_TYPE = "media_type"
        const val TIME_WINDOW = "time_window"
    }

    /**
     * Get the daily or weekly trending items. The daily trending list tracks items over the period
     * of a day while items have a 24 hour half life. The weekly list tracks items over a 7 day period,
     * with a 7 day half life.
     * https://developers.themoviedb.org/3/trending/get-trending
     *
     * @param apiKey Clave de la API (v3 auth)
     * @param mediaType define a mediatype
     * @param timeWindow define a timeWindow
     */
    @GET("trendin/{media_type}/{time_window}")
    fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String
    ): Deferred<DtoTrendingResponse>

}