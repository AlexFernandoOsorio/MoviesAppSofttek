package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import javax.inject.Inject

class InsertFavoriteMovieToDbUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(movieDetailModel: MovieDetailModel){
        movieRepository.insertMovieToLocal(movieDetailModel)
    }
}