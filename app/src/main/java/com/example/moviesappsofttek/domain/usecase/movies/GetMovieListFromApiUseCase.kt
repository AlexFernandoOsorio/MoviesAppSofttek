package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.models.movies.MovieModel
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import com.example.moviesappsofttek.core.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieListFromApiUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    // Caso de uso para obtener la lista de peliculas populares indexado por popularidad
    operator fun invoke(apiKey: String, page : Int) = flow<UIEvent<List<MovieModel>>> {
        emit(UIEvent.Loading())
        val movieList = movieRepository.getMovieListPopularFromRemote(apiKey,page)
        emit(UIEvent.Success(movieList))
    }.catch {
        // En caso de error se emite un evento de error
        emit(UIEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}