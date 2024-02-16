package com.example.moviesappsofttek.di

import com.example.moviesappsofttek.domain.repositories.AccountRepository
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import com.example.moviesappsofttek.domain.usecase.accounts.AccountUseCase
import com.example.moviesappsofttek.domain.usecase.movies.DeleteFavoriteMovieFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMovieByIdFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMoviesListFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieByIdFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieListFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMoviesByNameFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.InsertFavoriteMovieToDbUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesAccountUseCase(repository: AccountRepository): AccountUseCase {
        return AccountUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetMovieListFromApiUseCase(repository: MovieRepository): GetMovieListFromApiUseCase {
        return GetMovieListFromApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetMovieByIdFromApiUseCase(repository: MovieRepository): GetMovieByIdFromApiUseCase {
        return GetMovieByIdFromApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetMoviesByNameFromApiUseCase(repository: MovieRepository): GetMoviesByNameFromApiUseCase {
        return GetMoviesByNameFromApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesInsertFavoriteMovieToDbUseCase(repository: MovieRepository): InsertFavoriteMovieToDbUseCase {
        return InsertFavoriteMovieToDbUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetFavoriteMoviesListFromDbUseCase(repository: MovieRepository): GetFavoriteMoviesListFromDbUseCase {
        return GetFavoriteMoviesListFromDbUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetFavoriteMovieByIdFromDbUseCase(repository: MovieRepository): GetFavoriteMovieByIdFromDbUseCase {
        return GetFavoriteMovieByIdFromDbUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteFavoriteMovieFromDbUseCase(repository: MovieRepository): DeleteFavoriteMovieFromDbUseCase {
        return DeleteFavoriteMovieFromDbUseCase(repository)
    }
}