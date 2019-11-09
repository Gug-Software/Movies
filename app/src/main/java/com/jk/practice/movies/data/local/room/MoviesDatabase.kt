package com.jk.practice.movies.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jk.practice.movies.data.local.room.dao.MoviesDao
import com.jk.practice.movies.data.local.room.dao.ProductionCompanyDao
import com.jk.practice.movies.data.local.room.dao.SpokenLanguageDao
import com.jk.practice.movies.data.local.room.entities.DbGenre
import com.jk.practice.movies.data.local.room.entities.DbMovie
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany
import com.jk.practice.movies.data.local.room.entities.DbSpokenLanguage
import gug.co.com.moviemarket.data.local.room.dao.GenreDao

const val DB_NAME = "MoviesDb"

@Database(
    entities = [
        DbMovie::class,
        DbGenre::class,
        DbProductionCompany::class,
        DbSpokenLanguage::class
    ], version = 1, exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun genreDao(): GenreDao
    abstract fun productionCompaniesDao(): ProductionCompanyDao
    abstract fun spokenLanguagesDao(): SpokenLanguageDao

    companion object {

        private lateinit var INSTANCE: MoviesDatabase

        fun getDatabase(context: Context): MoviesDatabase {

            synchronized(MoviesDatabase::class.java) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MoviesDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }

            return INSTANCE

        }
    }
}