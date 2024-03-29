package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import com.example.moviesappsofttek.core.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieByIdFromApiUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    // Caso de uso para obtener una pelicula por su id
    operator fun invoke(movieId: String, apiKey: String) = flow<UIEvent<MovieDetailModel>> {
        emit(UIEvent.Loading())
        val movieDetail = movieRepository.getMovieByIdFromRemote(movieId, apiKey)
        emit(UIEvent.Success(movieDetail))
    }.catch {
        // En caso de error se emite un evento de error
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}