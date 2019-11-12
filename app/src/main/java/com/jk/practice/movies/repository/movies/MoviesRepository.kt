package com.jk.practice.movies.repository.movies

import com.jk.practice.movies.data.local.datastore.IMoviesLocalDataStore
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.mapper.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val remoteDataStore: ITrendingRemoteDataStore,
    private val localDataStore: IMoviesLocalDataStore,
    private val movieDbMapper: Mapper<DtoTrendingResponse.DtoMovie, DbMovie>,
    private val movieFromLocal: Mapper<DbMovie, Movie>,
    private val movieFromRemote: Mapper<DtoTrendingResponse.DtoMovie, Movie>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractMovies.Model {

    override suspend fun getTrendingMovies(): Result<List<Movie>> {

        return withContext(ioDispatcher) {

            try {
                // get the movies from remote source
                val moviesRemote = remoteDataStore.getTrending()
                moviesRemote.let { response ->
                    // we try save the movies in localdatabase
                    response.dtoMovies.forEach {
                        localDataStore.insertAll(movieDbMapper.mapFromTo(it))
                    }
                    val movies = response.dtoMovies.map { movieFromRemote.mapFromTo(it) }
                    return@withContext Result.Success(movies)
                }

            } catch (exc: Exception) {
                // if the endpoint fail, we try get the movies from localDataStore
                val moviesLocal = localDataStore.getTrendingMovies()
                (moviesLocal as Result.Success).let { movieLocal ->
                    val movies = movieLocal.data.map { movieFromLocal.mapFromTo(it) }
                    return@withContext Result.Success(movies)
                }
                return@withContext Result.Error(Exception("Illegal state"))
            }
        }


    }
}