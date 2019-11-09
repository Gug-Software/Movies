package com.jk.practice.movies.domain.mappers.movie

import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.mapper.Mapper

class MovieMapperFromLocal : Mapper<DbMovie, Movie>() {

    override fun mapFromTo(from: DbMovie): Movie {
        return Movie(
            id = from.id,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            releaseDate = from.releaseDate
        )
    }

}