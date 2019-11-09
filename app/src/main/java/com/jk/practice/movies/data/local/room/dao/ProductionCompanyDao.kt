package com.jk.practice.movies.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jk.practice.movies.data.local.room.entities.DbProductionCompany

@Dao
interface ProductionCompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dbProductionCompany: DbProductionCompany)

    @Query("SELECT * FROM DbProductionCompany WHERE movieId= :movieId")
    suspend fun getProductionCompaniesByMovie(movieId: Int): List<DbProductionCompany>

}