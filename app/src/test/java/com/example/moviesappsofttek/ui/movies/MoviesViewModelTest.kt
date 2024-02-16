package com.example.moviesappsofttek.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieListFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMoviesByNameFromApiUseCase
import com.example.moviesappsofttek.features.movies.MoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest{

    @RelaxedMockK
    private lateinit var getMovieListFromApiUseCase: GetMovieListFromApiUseCase
    @RelaxedMockK
    private lateinit var getMoviesByNameFromApiUseCase: GetMoviesByNameFromApiUseCase
    @RelaxedMockK
    lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(getMovieListFromApiUseCase, getMoviesByNameFromApiUseCase)
        Dispatchers.Unconfined
    }


    @Test
    fun getGetMovieListFromApiUseCase() {
        // Given
        moviesViewModel = MoviesViewModel(getMovieListFromApiUseCase, getMoviesByNameFromApiUseCase)

        // When
        val result = getMovieListFromApiUseCase.invoke("1234")

        // Then
        assert(result != null)
    }

    @Test
    fun getGetMoviesByNameFromApiUseCase() {
        // Given
        moviesViewModel = MoviesViewModel(getMovieListFromApiUseCase, getMoviesByNameFromApiUseCase)

        // When
        val result = getMoviesByNameFromApiUseCase.invoke("1234", "Batman")

        // Then
        assert(result != null)
    }
}