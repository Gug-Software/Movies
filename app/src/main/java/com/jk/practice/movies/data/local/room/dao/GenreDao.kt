package gug.co.com.moviemarket.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jk.practice.movies.data.local.room.entities.DbGenre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dbGenre: DbGenre)

    @Query("SELECT * FROM DbGenre WHERE movieId= :movieId")
    suspend fun getGenresByMovie(movieId: Int): List<DbGenre>

}