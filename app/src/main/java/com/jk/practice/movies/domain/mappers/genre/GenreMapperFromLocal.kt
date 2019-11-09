package com.jk.practice.movies.domain.mappers.genre

import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.mapper.Mapper
import gug.co.com.moviemarket.domain.details.Genre

class GenreMapperFromLocal : Mapper<DbGenre, Genre>() {

    override fun mapFromTo(from: DbGenre): Genre {
        return Genre(
            id = from.id,
            name = from.name
        )
    }

}