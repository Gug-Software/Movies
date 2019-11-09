package com.jk.practice.movies.domain.mappers.movie

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.mapper.Mapper

class MovieDetailMapperFromRemote : Mapper<DtoMovieDetail, MovieDetail>() {

    override fun mapFromTo(from: DtoMovieDetail): MovieDetail {
        return MovieDetail(
            id = from.id,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            budget = from.budget,
            originalLanguage = from.originalLanguage,
            overview = from.overview,
            popularity = from.popularity,
            releaseDate = from.releaseDate,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            genres = listOf(),
            companies = listOf(),
            languages = listOf()
        )
    }

}