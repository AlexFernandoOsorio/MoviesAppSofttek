package com.example.moviesappsofttek.ui.moviesdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.usecase.movies.DeleteFavoriteMovieFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMovieByIdFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieByIdFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.InsertFavoriteMovieToDbUseCase
import com.example.moviesappsofttek.features.moviesdetail.MovieDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class MovieDetailViewModelTest {

    @RelaxedMockK
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @RelaxedMockK
    private lateinit var getMovieByIdFromApiUseCase: GetMovieByIdFromApiUseCase
    @RelaxedMockK
    private lateinit var insertFavoriteMovieToDb: InsertFavoriteMovieToDbUseCase
    @RelaxedMockK
    private lateinit var getFavoriteMovieByIdFromDbUseCase: GetFavoriteMovieByIdFromDbUseCase
    @RelaxedMockK
    private lateinit var deleteFavoriteMovieFromDb: DeleteFavoriteMovieFromDbUseCase

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        movieDetailViewModel = MovieDetailViewModel(getMovieByIdFromApiUseCase, insertFavoriteMovieToDb, getFavoriteMovieByIdFromDbUseCase, deleteFavoriteMovieFromDb)
        Dispatchers.Unconfined
    }

    @Test
    fun getGetMovieDetailFromApiUseCase() = runBlocking {

        // Given
        movieDetailViewModel = MovieDetailViewModel(getMovieByIdFromApiUseCase, insertFavoriteMovieToDb, getFavoriteMovieByIdFromDbUseCase, deleteFavoriteMovieFromDb)

        // When
        val result = getMovieByIdFromApiUseCase("1234", "1234")

        // Then
        assert(result != null)
    }

    @Test
    fun getInsertFavoriteMovieToDbUseCase() = runBlocking {
        // Given
        movieDetailViewModel = MovieDetailViewModel(getMovieByIdFromApiUseCase, insertFavoriteMovieToDb, getFavoriteMovieByIdFromDbUseCase, deleteFavoriteMovieFromDb)
        val movie = MovieDetailModel(
            1234,
            "Batman",
            "2020",
            "www.google.com",
            10.0,
            "www.google.com",
            List(2, { "www.google.com" }),
        )
        // When
        val result =  coEvery { insertFavoriteMovieToDb(movie) }

        // Then
        assert(result != null)
    }
}