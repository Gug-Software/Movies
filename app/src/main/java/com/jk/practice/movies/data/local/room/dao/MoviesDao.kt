package com.jk.practice.movies.data.local.room.dao

import androidx.room.*
import com.jk.practice.movies.data.local.room.entities.DbMovie

@Dao
interface MoviesDao {

    @Query("SELECT * FROM DbMovie ")
    suspend fun getMovies(): List<DbMovie>

    @Query("SELECT * FROM DbMovie WHERE id=:movieId ")
    suspend fun getMovieByMovieId(movieId: Int): DbMovie

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg movies: DbMovie)

    @Update
    suspend fun update(movie: DbMovie)

}