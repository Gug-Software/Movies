package com.jk.practice.movies.di

import com.jk.practice.movies.data.local.datastore.IMoviesLocalDataStore
import com.jk.practice.movies.data.local.room.MoviesDatabase
import com.jk.practice.movies.data.local.room.dao.MoviesDao
import com.jk.practice.movies.data.local.room.datastores.movies.MoviesRoomDataStore
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.mappers.MovieDbMapper
import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.MoviesAPI
import com.jk.practice.movies.data.remote.retrofit.datastores.TrendingRetrofitDataStore
import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.domain.mappers.MovieMapperFromLocal
import com.jk.practice.movies.domain.mappers.MovieMapperFromRemote
import com.jk.practice.movies.domain.movies.Movie
import com.jk.practice.movies.repository.movies.MoviesRepository
import com.jk.practice.movies.utils.mapper.Mapper
import com.jk.practice.movies.viewmodels.movies.MoviesViewModel
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

            // Room Database
            single<MoviesDao> { MoviesDatabase.getDatabase(get()).moviesDao() }

            // Remote Data store with retrofit
            single<ITrendingRemoteDataStore> { TrendingRetrofitDataStore(get()) }

            // Local DataStores
            single<IMoviesLocalDataStore> { MoviesRoomDataStore(get()) }

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

            // ViewModels
            viewModel { MoviesViewModel(get()) }

            // Helpers
            single<Mapper<DtoTrendingResponse.DtoMovie, DbMovie>>(named("MoviesMapperToDb")) { MovieDbMapper() }
            single<Mapper<DbMovie, Movie>>(named("MoviesFromLocal")) { MovieMapperFromLocal() }
            single<Mapper<DtoTrendingResponse.DtoMovie, Movie>>(named("MoviesFromRemote")) { MovieMapperFromRemote() }

        }

    }
}