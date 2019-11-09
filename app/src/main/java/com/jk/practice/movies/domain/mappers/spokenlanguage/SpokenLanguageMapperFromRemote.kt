package com.jk.practice.movies.domain.mappers.spokenlanguage

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoSpokenLanguage
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.utils.mapper.Mapper

class SpokenLanguageMapperFromRemote : Mapper<DtoSpokenLanguage, SpokenLanguage>() {

    override fun mapFromTo(from: DtoSpokenLanguage): SpokenLanguage {
        return SpokenLanguage(
            name = from.name,
            iso6391 = from.iso6391
        )
    }

}