package com.jk.practice.movies.data.local.room.mappers

import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.utils.mapper.Mapper

class MovieDbMapper : Mapper<DtoTrendingResponse.DtoMovie, DbMovie>() {

    override fun mapFromTo(from: DtoTrendingResponse.DtoMovie): DbMovie {
        return DbMovie(
            id = from.id,
            adult = from.adult,
            backdropPath = from.backdropPath,
            firstAirDate = from.firstAirDate,
            mediaType = from.mediaType,
            name = from.name,
            originalLanguage = from.originalLanguage,
            originalName = from.originalName,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount
        )
    }
}