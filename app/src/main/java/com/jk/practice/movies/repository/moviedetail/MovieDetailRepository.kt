package com.jk.practice.movies.repository.moviedetail

import com.jk.practice.movies.data.local.datastore.IMovieDetailLocalDataStore
import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import com.jk.practice.movies.data.remote.datastore.IMovieDetailRemoteDataStore
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoGenre
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoMovieDetail
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoProductionCompany
import com.jk.practice.movies.data.remote.retrofit.dtos.moviedetail.DtoSpokenLanguage
import com.jk.practice.movies.domain.contracts.moviedetail.IContractMovieDetail
import com.jk.practice.movies.domain.domain.moviedetail.MovieDetail
import com.jk.practice.movies.domain.domain.moviedetail.ProductionCompany
import com.jk.practice.movies.domain.domain.moviedetail.SpokenLanguage
import com.jk.practice.movies.domain.domain.movies.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.jk.practice.movies.utils.Result
import com.jk.practice.movies.utils.mapper.Mapper
import gug.co.com.moviemarket.domain.details.Genre

class MovieDetailRepository(
    private val remoteDataStore: IMovieDetailRemoteDataStore,
    private val localDataStore: IMovieDetailLocalDataStore,
    private val movieDetailDbMapper: Mapper<DtoMovieDetail, DbMovie>,
    private val genreDbMapper: Mapper<DtoGenre, DbGenre>,
    private val companyDbMapper: Mapper<DtoProductionCompany, DbProductionCompany>,
    private val languageDbMapper: Mapper<DtoSpokenLanguage, DbSpokenLanguage>,
    private val movieDetailMapperFromRemote: Mapper<DtoMovieDetail, MovieDetail>,
    private val genreMapperFromRemote: Mapper<DtoGenre, Genre>,
    private val companyMapperFromRemote: Mapper<DtoProductionCompany, ProductionCompany>,
    private val languageMapperFromRemote: Mapper<DtoSpokenLanguage, SpokenLanguage>,
    private val movieDetailMapperFromLocal: Mapper<DbMovie, MovieDetail>,
    private val genreMapperFromLocal: Mapper<DbGenre, Genre>,
    private val companyMapperFromLocal: Mapper<DbProductionCompany, ProductionCompany>,
    private val languageMapperFromLocal: Mapper<DbSpokenLanguage, SpokenLanguage>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IContractMovieDetail.Model {

    /**
     * Always update the movie in databaselocal with the details from remote source
     * We need save in databaselocal the listdetail information
     */
    override suspend fun getMovieDetailById(movieId: Int): Result<MovieDetail> {

        return withContext(ioDispatcher) {

            try {
//                var movie =
//                    getDomainMovieFromLocal(movieId)
//
//                return@withContext Result.Success(movie)

                val movieRemote = remoteDataStore.getMovieDetail(movieId)
                movieRemote?.let { movieDetail ->
                    localDataStore.updateMovie(movieDetailDbMapper.mapFromTo(movieDetail))
                    saveGenres(movieDetail, movieId)
                    saveProductionCompanies(movieDetail, movieId)
                    saveSpokenLanguages(movieDetail, movieId)
                    val movie = getDomainMovieFromRemote(movieDetail)
                    return@withContext Result.Success(movie)
                }
            } catch (exc: Exception) {
                // if the remote data fails, we try get the info from local
                var movie =
                    getDomainMovieFromLocal(movieId)

                return@withContext Result.Success(movie)
            }
        }
    }

    private suspend fun getDomainMovieFromLocal(movieId: Int): MovieDetail {

        val movieLocal = localDataStore.getMovieById(movieId = movieId)
        val genresLocal = localDataStore.getGenresByMovieId(movieId = movieId)
        val companiesLocal =
            localDataStore.getProductionCompaniesByMovieId(movieId = movieId)
        val languagesLocal = localDataStore.getSpokenLaguageByMovieId(movieId = movieId)

        var movie = movieDetailMapperFromLocal.mapFromTo((movieLocal as Result.Success).data)
        movie.apply {
            this.genres = (genresLocal as Result.Success).data.map {
                genreMapperFromLocal.mapFromTo(it)
            }
            this.companies = (companiesLocal as Result.Success).data.map {
                companyMapperFromLocal.mapFromTo(it)
            }
            this.languages = (languagesLocal as Result.Success).data.map {
                languageMapperFromLocal.mapFromTo(it)
            }
        }
        return movie
    }

    private fun getDomainMovieFromRemote(
        dtoMovieDetail: DtoMovieDetail
    ): MovieDetail {

        return movieDetailMapperFromRemote.mapFromTo(dtoMovieDetail).apply {
            genres = dtoMovieDetail.dtoGenres.map {
                genreMapperFromRemote.mapFromTo(it)
            }
            companies = dtoMovieDetail.dtoProductionCompanies.map {
                companyMapperFromRemote.mapFromTo(it)
            }
            languages = dtoMovieDetail.dtoSpokenLanguages.map {
                languageMapperFromRemote.mapFromTo(it)
            }
        }

    }

    private suspend fun saveSpokenLanguages(
        movieDetail: DtoMovieDetail,
        movieId: Int
    ) {
        movieDetail.dtoSpokenLanguages.forEach {
            localDataStore.insertSpokenLanguages(
                languageDbMapper.mapFromTo(it).apply {
                    this.movieId = movieId
                }
            )
        }
    }

    private suspend fun saveProductionCompanies(
        movieDetail: DtoMovieDetail,
        movieId: Int
    ) {
        movieDetail.dtoProductionCompanies.forEach {
            localDataStore.insertProductionCompanies(
                companyDbMapper.mapFromTo(it).apply {
                    this.movieId = movieId
                }
            )
        }
    }

    private suspend fun saveGenres(
        movieDetail: DtoMovieDetail,
        movieId: Int
    ) {
        movieDetail.dtoGenres.forEach {
            localDataStore.insertGenres(
                genreDbMapper.mapFromTo(it).apply {
                    this.movieId = movieId
                }
            )
        }
    }

}