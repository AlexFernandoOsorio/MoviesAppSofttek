package com.example.moviesappsofttek.domain.usecase.movies


import com.example.moviesappsofttek.domain.repositories.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieByIdFromApiUseCaseTest {

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository

    lateinit var getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun whenGetMovieByIdFromApiUseCaseIsCalledThenReturnMovieDetailModel()= runBlocking {

        // Given
        getMovieByIdFromApiUseCase = GetMovieByIdFromApiUseCase(movieRepository)

        // When
        val result = getMovieByIdFromApiUseCase.invoke("1234", "1234")

        // Then
        assert(result != null)
    }
}