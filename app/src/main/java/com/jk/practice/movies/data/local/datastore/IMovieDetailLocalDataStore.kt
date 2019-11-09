package com.jk.practice.movies.data.local.datastore

import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import com.jk.practice.movies.utils.Result

interface IMovieDetailLocalDataStore {

    /**
     * Update the movie in databaselocal with detail information
     */
    suspend fun updateMovie(movie: DbMovie)

    /**
     * Insert the movie´s genres
     */
    suspend fun insertGenres(vararg genres: DbGenre)

    /**
     * Insert the movie´s production companies
     */
    suspend fun insertProductionCompanies(vararg productionCompanies: DbProductionCompany)

    /**
     * Insert the movie´s Spoken Languages
     */
    suspend fun insertSpokenLanguages(vararg spokenLanguages: DbSpokenLanguage)

    /**
     * Get all the movie information for movieid
     */
    suspend fun getMovieById(movieId: Int): Result<DbMovie>

    /**
     * get all the genres of movie
     */
    suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>>

    /**
     * get all the laguages for movie
     */
    suspend fun getSpokenLaguageByMovieId(movieId: Int): Result<List<DbSpokenLanguage>>

    /**
     * get all de production companies by movie
     */
    suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>>

}