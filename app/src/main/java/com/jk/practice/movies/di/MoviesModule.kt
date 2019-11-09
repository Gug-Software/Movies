package com.jk.practice.movies.di

import com.jk.practice.movies.data.local.datastore.IMovieDetailLocalDataStore
import com.jk.practice.movies.data.local.datastore.IMoviesLocalDataStore
import com.jk.practice.movies.data.local.room.MoviesDatabase
import com.jk.practice.movies.data.local.room.dao.MoviesDao
import com.jk.practice.movies.data.local.room.dao.ProductionCompanyDao
import com.jk.practice.movies.data.local.room.dao.SpokenLanguageDao
import com.jk.practice.movies.data.local.room.datastores.moviedetail.MovieDetailRoomDataStore
import com.jk.practice.movies.data.local.room.datastores.movies.MoviesRoomDataStore
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.mappers.MovieDbMapper
import com.jk.practice.movies.data.remote.datastore.IMovieDetailRemoteDataStore
import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.MovieDetailAPI
import com.jk.practice.movies.data.remote.retrofit.MoviesAPI
import com.jk.practice.movies.data.remote.retrofit.datastores.MovieDetailRetrofitDataStore
import com.jk.practice.movies.data.remote.retrofit.datastores.TrendingRetrofitDataStore
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.contracts.moviedetail.IContractMovieDetail
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.domain.mappers.movie.MovieDetailMapperFromLocal
import com.jk.practice.movies.domain.mappers.movie.MovieDetailMapperFromRemote
import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.domain.mappers.movie.MovieMapperFromLocal
import com.jk.practice.movies.repository.moviedetail.MovieDetailRepository
import com.jk.practice.movies.repository.movies.MoviesRepository
import com.jk.practice.movies.utils.mapper.Mapper
import com.jk.practice.movies.viewmodels.moviedetail.MovieDetailViewModel
import com.jk.practice.movies.viewmodels.movies.MoviesViewModel
import gug.co.com.moviemarket.data.local.room.dao.GenreDao
import gug.co.com.networkmoviemarketlib.data.source.remote.retrofit.MoviesRetrofit
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MoviesModule {

    companion object {

        val appModule = module {

            // Retrofit instance for moviesApi remote
            single<MoviesAPI> { MoviesRetrofit.moviesApi }
            single<MovieDetailAPI> { MoviesRetrofit.movieDetailApi }

            // Room Database
            single<MoviesDao> { MoviesDatabase.getDatabase(get()).moviesDao() }
            single<GenreDao> { MoviesDatabase.getDatabase(get()).genreDao() }
            single<ProductionCompanyDao> {
                MoviesDatabase.getDatabase(get()).productionCompaniesDao()
            }
            single<SpokenLanguageDao> { MoviesDatabase.getDatabase(get()).spokenLanguagesDao() }

            // Remote Data store with retrofit
            single<ITrendingRemoteDataStore> { TrendingRetrofitDataStore(get()) }
            single<IMovieDetailRemoteDataStore> { MovieDetailRetrofitDataStore(get()) }

            // Local DataStores
            single<IMoviesLocalDataStore> { MoviesRoomDataStore(get()) }
            single<IMovieDetailLocalDataStore> {
                MovieDetailRoomDataStore(
                    get(),
                    get(),
                    get(),
                    get(),
                    Dispatchers.IO
                )
            }

            // Repositories
            single<IContractMovies.Model> {
                MoviesRepository(
                    get(),
                    get(),
                    get(named("MoviesMapperToDb")),
                    get(named("MoviesFromLocal")),
                    get(named("MoviesFromRemote")),
                    Dispatchers.IO
                )
            }

            single<IContractMovieDetail.Model> {
                MovieDetailRepository(
                    get(),
                    get(),
                    get(named("MovieDetailMapperToDb")),
                    get(named("GenreMapperToDb")),
                    get(named("CompanyMapperToDb")),
                    get(named("LanguageMapperToDb")),
                    get(named("MovieDetailFromRemote")),
                    get(named("GenreFromRemote")),
                    get(named("CompanyFromRemote")),
                    get(named("LanguageFromRemote")),
                    Dispatchers.IO
                )
            }

            // ViewModels
            viewModel { MoviesViewModel(get()) }
            viewModel { MovieDetailViewModel(get()) }


        }

    }
}