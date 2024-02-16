package com.example.moviesappsofttek.di

import com.example.moviesappsofttek.domain.repositories.AccountRepository
import com.example.moviesappsofttek.domain.usecase.accounts.AccountUseCase
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

}