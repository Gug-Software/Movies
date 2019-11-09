package com.jk.practice.movies.domain.contracts.movies

import com.jk.practice.movies.domain.domain.movies.Movie
import com.jk.practice.movies.utils.Result

interface IContractMovies {

    interface View {

        fun navigateToMovieDetail(movieId: Int)

    }

    interface ViewModel {

        fun loadMovies()

        fun showMovieDetail(movie: Movie)

    }

    interface Model {

        /**
         * get the trending movies from remote
         */
        suspend fun getTrendingMovies(): Result<List<Movie>>

    }

}