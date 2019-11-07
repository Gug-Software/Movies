package com.jk.practice.movies.domain.contracts.movies

import com.jk.practice.movies.data.remote.retrofit.dtos.movies.DtoTrendingResponse
import com.jk.practice.movies.utils.Result

interface IContractMovies {

    interface View {


    }

    interface ViewModel {

        fun loadMovies()

    }

    interface Model {

        /**
         * get the trending movies from remote
         */
        suspend fun getTrendingMovies(): Result<List<DtoTrendingResponse.DtoMovie>>

    }

}