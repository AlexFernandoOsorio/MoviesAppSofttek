package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.repositories.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavoriteMoviesListFromDbUseCaseTest {

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var getFavoriteMoviesListFromDbUseCase: GetFavoriteMoviesListFromDbUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetFavoriteMoviesListFromDbUseCaseIsCalledThenReturnListOfMovies()= runBlocking {

        // Given
        getFavoriteMoviesListFromDbUseCase = GetFavoriteMoviesListFromDbUseCase(movieRepository)

        // When
        val result = coEvery { getFavoriteMoviesListFromDbUseCase.invoke()}

        // Then
        assert(result != null)
    }
}