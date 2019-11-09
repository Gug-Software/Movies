package com.jk.practice.movies.data.local.room.mappers

import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoGenre
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoSpokenLanguage
import com.jk.practice.movies.utils.mapper.Mapper

class SpokenLanguageDbMapper : Mapper<DtoSpokenLanguage, DbSpokenLanguage>() {

    override fun mapFromTo(from: DtoSpokenLanguage): DbSpokenLanguage {
        return DbSpokenLanguage(
            movieId = 0,
            iso6391 = from.iso6391,
            name = from.name
        )
    }
}