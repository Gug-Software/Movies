package com.jk.practice.movies.domain.contracts.moviedetail

import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import gug.co.com.moviemarket.domain.details.Genre
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.utils.Result

interface IContractMovieDetail {

    interface View {

    }

    interface ViewModel {

        fun loadMovieDetail(movieId: Int)

    }

    interface Model {

        suspend fun getMovieDetailById(movieId: Int): Result<MovieDetail>

    }

}