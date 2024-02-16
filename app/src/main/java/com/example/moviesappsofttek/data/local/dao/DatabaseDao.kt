package com.example.moviesappsofttek.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesappsofttek.data.local.entities.AccountEntity
import com.example.moviesappsofttek.data.local.entities.MovieEntity


@Dao
interface DatabaseDao {

    @Query("SELECT * FROM table_movies ORDER BY tm_release_date DESC")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM table_movies WHERE tm_id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)


    // Querys para accounts

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: AccountEntity)

    @Query("SELECT * FROM table_account WHERE ta_id = :id AND ta_user = :username AND ta_password = :password")
    suspend fun getAccountById(id : Int, username : String,password : String): AccountEntity

}