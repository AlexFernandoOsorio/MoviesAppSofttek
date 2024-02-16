package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class GetFavoriteMoviesListFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    // Caso de uso para obtener la lista de peliculas favoritas de la base de datos local
    suspend operator fun invoke(): List<MovieDetailModel> {
        return movieRepository.getMovieListFromLocal()
    }
}