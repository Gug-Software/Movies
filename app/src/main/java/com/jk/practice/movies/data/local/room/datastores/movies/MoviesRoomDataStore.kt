package com.jk.practice.movies.data.local.room.datastores.movies

import com.jk.practice.movies.data.local.datastore.IMoviesLocalDataStore
import com.jk.practice.movies.data.local.room.dao.MoviesDao
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRoomDataStore(
    private val moviesDao: MoviesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IMoviesLocalDataStore {

    override suspend fun getTrendingMovies(): Result<List<DbMovie>> = withContext(ioDispatcher) {

        return@withContext try {
            Result.Success(moviesDao.getMovies())
        } catch (e: Exception) {
            Error(e)
        } as Result<List<DbMovie>>

    }

    override suspend fun insertAll(vararg movies: DbMovie) = withContext(ioDispatcher) {
        try {
            moviesDao.insertAll(*movies)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}