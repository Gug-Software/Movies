package com.jk.practice.movies.domain.mappers.genre

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoGenre
import com.jk.practice.movies.utils.mapper.Mapper
import gug.co.com.moviemarket.domain.details.Genre

class GenreMapperFromRemote : Mapper<DtoGenre, Genre>() {

    override fun mapFromTo(from: DtoGenre): Genre {
        return Genre(
            id = from.id,
            name = from.name
        )
    }

}