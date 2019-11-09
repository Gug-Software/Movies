package com.jk.practice.movies.data.local.room.mappers

import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoProductionCompany
import com.jk.practice.movies.utils.mapper.Mapper

class ProductionCompanyDbMapper : Mapper<DtoProductionCompany, DbProductionCompany>() {

    override fun mapFromTo(from: DtoProductionCompany): DbProductionCompany {
        return DbProductionCompany(
            movieId = 0,
            id = from.id,
            logoPath = from.logoPath,
            name = from.name,
            originCountry = from.originCountry
        )
    }
}