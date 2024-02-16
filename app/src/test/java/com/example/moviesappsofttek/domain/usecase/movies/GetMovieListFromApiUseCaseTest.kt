package com.example.moviesappsofttek.domain.usecase.movies

import com.example.moviesappsofttek.domain.repositories.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetMovieListFromApiUseCaseTest{

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getMovieListFromApiUseCase: GetMovieListFromApiUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetMovieListFromApiUseCaseIsCalledThenReturnListOfMovies()= runBlocking {

        // Given
        getMovieListFromApiUseCase = GetMovieListFromApiUseCase(movieRepository)

        // When
        val result = getMovieListFromApiUseCase.invoke("1234")

        // Then
        assert(result != null)
    }
}