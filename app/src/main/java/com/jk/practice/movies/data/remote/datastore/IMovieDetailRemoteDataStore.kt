package com.jk.practice.movies.data.remote.datastore

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail

interface IMovieDetailRemoteDataStore {

    /**
     * get the movie detail for movie ID
     * @param movieId movie´s id
     * @return the movie´s details
     */
    suspend fun getMovieDetail(movieId: Int): DtoMovieDetail

}