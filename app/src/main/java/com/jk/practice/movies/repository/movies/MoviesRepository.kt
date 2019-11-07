package com.jk.practice.movies.repository.movies

import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MoviesRepository(
    private val trendingRemoteDataStore: ITrendingRemoteDataStore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractMovies.Model {

    override suspend fun getTrendingMovies(): Result<List<DtoTrendingResponse.DtoMovie>> {

        try {
            val moviesRemote = trendingRemoteDataStore.getTrending()
            return Result.Success(moviesRemote.dtoMovies)
        } catch (exc: Exception) {
            throw Exception("Ilegal State")
        }

    }
}