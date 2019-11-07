package com.jk.practice.movies.data.remote.retrofit.datastores

import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.MoviesAPI
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.API_KEY
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.MEDIA_TYPE_MOVIES
import com.jk.practice.movies.data.remote.retrofit.MoviesAPIConstants.Companion.TIME_WINDOW_DAY
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrendingRetrofitDataStore(
    private val moviesAPI: MoviesAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITrendingRemoteDataStore {

    override suspend fun getTrending(): DtoTrendingResponse {

        return withContext(ioDispatcher) {
            try {
                moviesAPI.getTrendingMovies(
                    API_KEY,
                    MEDIA_TYPE_MOVIES,
                    TIME_WINDOW_DAY
                ).await()
            } catch (e: Exception) {
                throw Exception("Illegal state")
            }
        }

    }

}