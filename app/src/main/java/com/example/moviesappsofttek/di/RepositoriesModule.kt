package com.example.moviesappsofttek.di

import com.example.moviesappsofttek.data.local.dao.DatabaseDao
import com.example.moviesappsofttek.data.remote.services.ApiServiceMovie
import com.example.moviesappsofttek.data.repositories.AccountRepositoryImpl
import com.example.moviesappsofttek.data.repositories.MovieRepositoryimpl
import com.example.moviesappsofttek.domain.repositories.AccountRepository
import com.example.moviesappsofttek.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        apiServiceMovie: ApiServiceMovie,
        databaseDao: DatabaseDao
    ): MovieRepository {
        return MovieRepositoryimpl(apiServiceMovie, databaseDao)
    }

    @Provides
    @Singleton
    fun providesAccountRepository(databaseDao: DatabaseDao): AccountRepository {
        return AccountRepositoryImpl(databaseDao)
    }
}