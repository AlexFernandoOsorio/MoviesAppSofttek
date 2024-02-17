package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class InsertFavoriteMovieToDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    // Caso de uso para insertar una pelicula a la base de datos local
    suspend operator fun invoke(movieDetailModel: MovieDetailModel) {
        movieRepository.insertMovieToLocal(movieDetailModel)
    }
}