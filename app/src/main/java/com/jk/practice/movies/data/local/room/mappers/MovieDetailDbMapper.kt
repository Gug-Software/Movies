package com.jk.practice.movies.data.local.room.mappers

import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.utils.mapper.Mapper

class MovieDetailDbMapper : Mapper<DtoMovieDetail, DbMovie>() {

    override fun mapFromTo(from: DtoMovieDetail): DbMovie {
        return DbMovie(
            id = from.id,
            backdropPath = from.backdropPath,
            budget = from.budget,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            releaseDate = from.releaseDate,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount
        )
    }
}