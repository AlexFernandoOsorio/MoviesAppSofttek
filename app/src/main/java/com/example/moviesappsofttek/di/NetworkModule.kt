package com.example.moviesappsofttek.di

import com.example.moviesappsofttek.data.remote.services.ApiServiceMovie
import com.example.moviesappsofttek.core.utils.GlobalConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiServiceMovie(retrofit: Retrofit): ApiServiceMovie {
        return provideRetrofit().create(ApiServiceMovie::class.java)
    }
}