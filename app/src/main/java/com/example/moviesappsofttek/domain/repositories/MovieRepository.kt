package com.example.moviesappsofttek.domain.repositories

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.models.movies.MovieModel

interface MovieRepository {


    suspend fun getMovieListPopularFromRemote(apiKey: String):List<MovieModel>

    suspend fun getMovieByIdFromRemote(movieId: String, apiKey: String): MovieDetailModel

    suspend fun getMovieByNameFromRemote(apiKey: String, name: String): List<MovieModel>
    suspend fun getMovieListFromLocal(): List<MovieDetailModel>

    suspend fun getMovieByIdFromLocal(id : Int): MovieDetailModel
    suspend fun insertMovieToLocal(movie : MovieDetailModel)

    suspend fun deleteMovieFromLocal(movie : MovieDetailModel)

}