package com.jk.practice.movies.data.remote.retrofit.datastores

import com.jk.practice.movies.data.remote.datastore.IMovieDetailRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.MovieDetailAPI
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.API_KEY
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.LANGUAGE_ES
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRetrofitDataStore(
    private val movieDetailAPI: MovieDetailAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IMovieDetailRemoteDataStore {

    override suspend fun getMovieDetail(movieId: Int): DtoMovieDetail = withContext(ioDispatcher) {
        return@withContext try {
            movieDetailAPI.getMovieDetail(movieId, API_KEY, LANGUAGE_ES).await()
        } catch (e: Exception) {
            throw Exception("Illegal state")
        }
    }

}