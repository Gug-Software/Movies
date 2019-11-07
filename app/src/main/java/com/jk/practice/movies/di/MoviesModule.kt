package com.jk.practice.movies.di

import com.jk.practice.movies.data.remote.datastore.ITrendingRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.MoviesAPI
import com.jk.practice.movies.data.remote.retrofit.datastores.TrendingRetrofitDataStore
import com.jk.practice.movies.domain.contracts.movies.IContractMovies
import com.jk.practice.movies.repository.movies.MoviesRepository
import com.jk.practice.movies.viewmodels.movies.MoviesViewModel
import gug.co.com.networkmoviemarketlib.data.source.remote.retrofit.MoviesRetrofit
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MoviesModule {

    companion object {

        val appModule = module {

            // Retrofit instance for moviesApi remote
            single<MoviesAPI> { MoviesRetrofit.moviesApi }

            // Remote Data store with retrofit
            single<ITrendingRemoteDataStore> { TrendingRetrofitDataStore(get()) }

            // Repositories
            single<IContractMovies.Model> { MoviesRepository(get()) }

            // ViewModels
            viewModel { MoviesViewModel(get()) }

        }

    }
}