package com.jk.practice.movies.utilstests

import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.domain.domain.movies.Movie
import gug.co.com.moviemarket.domain.details.Genre

class DomainTestUtils {

    companion object {

        fun movieForTest(i: Int): Movie {
            return Movie(
                id = i,
                backdropPath = "backdropPath-$i",
                originalTitle = "originalTitle-$i",
                releaseDate = "releaseDate-$i"
            )
        }

        fun movieDetailForTest(i: Int): MovieDetail {
            return MovieDetail(
                id = i,
                originalTitle = "originalTitle-$i",
                backdropPath = "backdropPath-$i",
                voteAverage = 6.7,
                overview = "overview-$i",
                releaseDate = "releaseDate-$i",
                budget = 1000000,
                originalLanguage = "es",
                popularity = 1000.0,
                voteCount = 1000,
                genres = genresForMovieDetail(),
                companies = companiesForMovieDetail(),
                languages = languagesForMovieDetail()
            )
        }

        private fun languagesForMovieDetail(): List<SpokenLanguage> {
            return listOf(
                SpokenLanguage(iso6391 = "iso", name = "name"),
                SpokenLanguage(iso6391 = "iso", name = "name")
            )
        }

        private fun companiesForMovieDetail(): List<ProductionCompany> {
            return listOf(
                ProductionCompany(
                    id = 1,
                    logoPath = "logoPath",
                    name = "name",
                    originCountry = "co"
                ),
                ProductionCompany(
                    id = 2,
                    logoPath = "logoPath",
                    name = "name",
                    originCountry = "co"
                )
            )
        }

        private fun genresForMovieDetail(): List<Genre> {
            return listOf(
                Genre(id = 1, name = "genreName"),
                Genre(id = 2, name = "genreName")
            )
        }


    }


}