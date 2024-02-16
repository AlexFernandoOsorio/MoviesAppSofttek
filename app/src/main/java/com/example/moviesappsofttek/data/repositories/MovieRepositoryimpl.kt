package com.example.moviesappsofttek.data.repositories

import com.example.moviesappsofttek.data.local.dao.DatabaseDao
import com.example.moviesappsofttek.data.local.entities.MovieEntity
import com.example.moviesappsofttek.data.mappers.toDomainMovieDetail
import com.example.moviesappsofttek.data.mappers.toDomainMovieList
import com.example.moviesappsofttek.data.remote.services.ApiServiceMovie
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.models.movies.MovieModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryimpl @Inject constructor(
    private val apiServiceMovie: ApiServiceMovie,
    private val movieDao: DatabaseDao
) : MovieRepository {


    override suspend fun getMovieListPopularFromRemote(apiKey: String): List<MovieModel> {
        return apiServiceMovie.getMoviesPopular(apiKey).toDomainMovieList()
    }

    override suspend fun getMovieByIdFromRemote(movieId: String, apiKey: String): MovieDetailModel {
        return apiServiceMovie.getMoviesById(movieId, apiKey).toDomainMovieDetail()
    }

    override suspend fun getMovieByNameFromRemote(apiKey: String, name: String): List<MovieModel> {
        return apiServiceMovie.getMoviesByName(apiKey, name).toDomainMovieList()
    }

    override suspend fun getMovieByIdFromLocal(id: Int): MovieDetailModel {
        return movieDao.getMovieById(id).toDomainMovieList()
    }

    override suspend fun getMovieListFromLocal(): List<MovieDetailModel> {
        val response: List<MovieEntity> = movieDao.getAllMovies()

        return response.map {
            MovieDetailModel(
                id = it.id,
                title = it.name,
                image = it.image,
                description = it.description,
                popularity = it.popularity.toDouble(),
                release_date = it.release_date,
                genre_ids = it.genre.split(",").map { it }
            )
        }
    }

    override suspend fun insertMovieToLocal(movie: MovieDetailModel) {
        movieDao.insertMovie(
            MovieEntity(
                id = movie.id,
                name = movie.title,
                image = movie.image,
                description = movie.description,
                popularity = movie.popularity.toString(),
                release_date = movie.release_date,
                genre = movie.genre_ids.joinToString(","),
                isFavorite = true
            )
        )
    }

    override suspend fun deleteMovieFromLocal(movie: MovieDetailModel) {
        movieDao.deleteMovie(
            MovieEntity(
                id = movie.id,
                name = movie.title,
                image = movie.image,
                description = movie.description,
                popularity = movie.popularity.toString(),
                release_date = movie.release_date,
                genre = movie.genre_ids.joinToString(","),
                isFavorite = true
            )
        )
    }
}