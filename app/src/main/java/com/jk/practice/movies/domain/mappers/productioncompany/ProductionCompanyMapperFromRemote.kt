package com.jk.practice.movies.domain.mappers.productioncompany

import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.utils.mapper.Mapper

class ProductionCompanyMapperFromRemote : Mapper<DtoProductionCompany, ProductionCompany>() {

    override fun mapFromTo(from: DtoProductionCompany): ProductionCompany {
        return ProductionCompany(
            id = from.id,
            name = from.name,
            logoPath = from.logoPath,
            originCountry = from.originCountry
        )
    }

}