package com.jk.practice.movies.data.local.room.mappers

import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoGenre
import com.jk.practice.movies.utils.mapper.Mapper

class GenreDbMapper : Mapper<DtoGenre, DbGenre>() {

    override fun mapFromTo(from: DtoGenre): DbGenre {
        return DbGenre(
            id = from.id,
            name = from.name,
            movieId = 0
        )
    }
}