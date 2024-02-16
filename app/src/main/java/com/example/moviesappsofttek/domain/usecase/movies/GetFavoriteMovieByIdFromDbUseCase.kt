package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class GetFavoriteMovieByIdFromDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {
        suspend operator fun invoke(id : Int) = movieRepository.getMovieByIdFromLocal(id)?.let {
            it
        }
}
