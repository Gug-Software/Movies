package com.jk.practice.movies.domain.mappers.spokenlanguage

import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.utils.mapper.Mapper

class SpokenLanguageMapperFromLocal : Mapper<DbSpokenLanguage, SpokenLanguage>() {

    override fun mapFromTo(from: DbSpokenLanguage): SpokenLanguage {
        return SpokenLanguage(
            name = from.name,
            iso6391 = from.iso6391
        )
    }

}