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

    // Querys para movies
    @Query("SELECT * FROM table_movies ORDER BY tm_release_date DESC")
    suspend fun getAllMovies(): List<MovieEntity>

    //query para obtener una pelicula por id
    @Query("SELECT * FROM table_movies WHERE tm_id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    //query para insertar una pelicula
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    //query para eliminar una pelicula
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)


    // Querys para accounts
    //query para insertar una cuenta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: AccountEntity)

    //query para obtener una cuenta por id
    @Query("SELECT * FROM table_account WHERE ta_id = :id AND ta_user = :username AND ta_password = :password")
    suspend fun getAccountById(id: Int, username: String, password: String): AccountEntity

}