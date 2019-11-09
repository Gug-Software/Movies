package com.jk.practice.movies.di

import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import com.jk.practice.movies.data.local.room.mappers.*
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoGenre
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoProductionCompany
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoSpokenLanguage
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.domain.mappers.genre.GenreMapperFromLocal
import com.jk.practice.movies.domain.mappers.genre.GenreMapperFromRemote
import com.jk.practice.movies.domain.mappers.movie.MovieDetailMapperFromLocal
import com.jk.practice.movies.domain.mappers.movie.MovieDetailMapperFromRemote
import com.jk.practice.movies.domain.mappers.movie.MovieMapperFromLocal
import com.jk.practice.movies.domain.mappers.movie.MovieMapperFromRemote
import com.jk.practice.movies.domain.mappers.productioncompany.ProductionCompanyMapperFromLocal
import com.jk.practice.movies.domain.mappers.productioncompany.ProductionCompanyMapperFromRemote
import com.jk.practice.movies.domain.mappers.spokenlanguage.SpokenLanguageMapperFromLocal
import com.jk.practice.movies.domain.mappers.spokenlanguage.SpokenLanguageMapperFromRemote
import com.jk.practice.movies.utils.mapper.Mapper
import gug.co.com.moviemarket.domain.details.Genre
import org.koin.core.qualifier.named
import org.koin.dsl.module

val helpersModule = module {

    // Movies
    single<Mapper<DtoTrendingResponse.DtoMovie, DbMovie>>(named("MoviesMapperToDb")) {
        MovieDbMapper()
    }
    single<Mapper<DbMovie, Movie>>(named("MoviesFromLocal")) {
        MovieMapperFromLocal()
    }
    single<Mapper<DtoTrendingResponse.DtoMovie, Movie>>(named("MoviesFromRemote")) {
        MovieMapperFromRemote()
    }

    // MovieDetail
    single<Mapper<DtoMovieDetail, DbMovie>>(named("MovieDetailMapperToDb")) {
        MovieDetailDbMapper()
    }
    single<Mapper<DbMovie, MovieDetail>>(named("MovieDetailFromLocal")) {
        MovieDetailMapperFromLocal()
    }
    single<Mapper<DtoMovieDetail, MovieDetail>>(named("MovieDetailFromRemote")) {
        MovieDetailMapperFromRemote()
    }

    // Genres
    single<Mapper<DtoGenre, DbGenre>>(named("GenreMapperToDb")) {
        GenreDbMapper()
    }
    single<Mapper<DbGenre, Genre>>(named("GenreFromLocal")) {
        GenreMapperFromLocal()
    }
    single<Mapper<DtoGenre, Genre>>(named("GenreFromRemote")) {
        GenreMapperFromRemote()
    }

    // ProductionCompanies
    single<Mapper<DtoProductionCompany, DbProductionCompany>>(named("CompanyMapperToDb")) {
        ProductionCompanyDbMapper()
    }
    single<Mapper<DbProductionCompany, ProductionCompany>>(named("CompanyFromLocal")) {
        ProductionCompanyMapperFromLocal()
    }
    single<Mapper<DtoProductionCompany, ProductionCompany>>(named("CompanyFromRemote")) {
        ProductionCompanyMapperFromRemote()
    }

    // Spoken Languages
    single<Mapper<DtoSpokenLanguage, DbSpokenLanguage>>(named("LanguageMapperToDb")) {
        SpokenLanguageDbMapper()
    }
    single<Mapper<DbSpokenLanguage, SpokenLanguage>>(named("LanguageFromLocal")) {
        SpokenLanguageMapperFromLocal()
    }
    single<Mapper<DtoSpokenLanguage, SpokenLanguage>>(named("LanguageFromRemote")) {
        SpokenLanguageMapperFromRemote()
    }


}