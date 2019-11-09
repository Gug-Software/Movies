package com.jk.practice.movies.domain.mappers.movie

import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.mapper.Mapper

class MovieMapperFromRemote : Mapper<DtoTrendingResponse.DtoMovie, Movie>() {

    override fun mapFromTo(from: DtoTrendingResponse.DtoMovie): Movie {
        return Movie(
            id = from.id,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            releaseDate = from.releaseDate
        )
    }

}