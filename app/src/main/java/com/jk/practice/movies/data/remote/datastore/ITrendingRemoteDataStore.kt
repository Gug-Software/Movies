package com.jk.practice.movies.data.remote.datastore

import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse

interface ITrendingRemoteDataStore {

    /**
     * Get the trending items
     */
    suspend fun getTrending(): DtoTrendingResponse
}