package com.jk.practice.movies.data.local.room.datastores.moviedetail

import com.jk.practice.movies.data.local.datastore.IMovieDetailLocalDataStore
import com.jk.practice.movies.data.local.room.dao.MoviesDao
import com.jk.practice.movies.data.local.room.dao.ProductionCompanyDao
import com.jk.practice.movies.data.local.room.dao.SpokenLanguageDao
import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import gug.co.com.moviemarket.data.local.room.dao.GenreDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.jk.practice.movies.utils.Result

class MovieDetailRoomDataStore(
    private val moviesDao: MoviesDao,
    private val genreDao: GenreDao,
    private val productionCompanyDao: ProductionCompanyDao,
    private val spokenLanguageDao: SpokenLanguageDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IMovieDetailLocalDataStore {

    override suspend fun insertGenres(vararg genres: DbGenre) = withContext(ioDispatcher) {
        genreDao.insertAll(*genres)
    }

    override suspend fun insertProductionCompanies(vararg productionCompanies: DbProductionCompany) =
        withContext(ioDispatcher) {
            productionCompanyDao.insertAll(*productionCompanies)
        }

    override suspend fun insertSpokenLanguages(vararg spokenLanguages: DbSpokenLanguage) =
        withContext(ioDispatcher) {
            spokenLanguageDao.insertAll(*spokenLanguages)
        }

    override suspend fun getMovieById(movieId: Int): Result<DbMovie> = withContext(ioDispatcher) {
        try {
            val movie = moviesDao.getMovieByMovieId(movieId)
            return@withContext Result.Success(movie)
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    } as Result<DbMovie>

    override suspend fun getGenresByMovieId(movieId: Int): Result<List<DbGenre>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(genreDao.getGenresByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            } as Result<List<DbGenre>>
        }

    override suspend fun getSpokenLaguageByMovieId(movieId: Int): Result<List<DbSpokenLanguage>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(spokenLanguageDao.getSpokenLanguagesByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            } as Result<List<DbSpokenLanguage>>
        }

    override suspend fun getProductionCompaniesByMovieId(movieId: Int): Result<List<DbProductionCompany>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(productionCompanyDao.getProductionCompaniesByMovie(movieId))
            } catch (e: Exception) {
                Error(e)
            } as Result<List<DbProductionCompany>>
        }

    override suspend fun updateMovie(movie: DbMovie) = withContext(ioDispatcher) {
        moviesDao.update(movie)
    }
}