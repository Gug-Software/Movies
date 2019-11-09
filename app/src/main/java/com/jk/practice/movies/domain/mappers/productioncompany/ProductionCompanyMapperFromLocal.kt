package com.jk.practice.movies.domain.mappers.productioncompany

import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.utils.mapper.Mapper

class ProductionCompanyMapperFromLocal : Mapper<DbProductionCompany, ProductionCompany>() {

    override fun mapFromTo(from: DbProductionCompany): ProductionCompany {
        return ProductionCompany(
            id = from.id,
            name = from.name,
            logoPath = from.logoPath,
            originCountry = from.originCountry
        )
    }

}