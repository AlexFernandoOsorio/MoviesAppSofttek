package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class GetFavoriteMovieByIdFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    // Caso de uso para obtener una pelicula por id de la base de datos local
    suspend operator fun invoke(id: Int) {
        movieRepository.getMovieByIdFromLocal(id)
    }
}
