package com.jk.practice.movies.data.local.datastore

import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.utils.Result

interface IMoviesLocalDataStore {

    /**
     * get the movies from a local datastore
     * @return movie´s list
     *
     */
    suspend fun getTrendingMovies(): Result<List<DbMovie>>

    /**
     * Insert movies in a local storage
     * @param movies the movies
     *
     */
    suspend fun insertAll(vararg movies: DbMovie)


}
