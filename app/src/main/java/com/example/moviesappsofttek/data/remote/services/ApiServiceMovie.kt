package com.example.moviesappsofttek.data.remote.services

import com.example.moviesappsofttek.data.remote.models.movies.MovieListResponse
import com.example.moviesappsofttek.data.remote.models.moviesDetail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMovie {

    @GET("3/movie/upcoming")
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String,
        @Query("page") pageIndex: Int
        ): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMoviesById(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetailDto

    @GET("3/search/movie")
    suspend fun getMoviesByName(
        @Query("api_key") apiKey: String,
        @Query("query") name: String
    ): MovieListResponse
}