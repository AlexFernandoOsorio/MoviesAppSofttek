package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class DeleteFavoriteMovieFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    // Caso de uso para eliminar una pelicula de la base de datos local
    suspend operator fun invoke(movie: MovieDetailModel) {
        movieRepository.deleteMovieFromLocal(movie)
    }
}