package com.example.moviesappsofttek.di

import android.content.Context
import androidx.room.Room
import com.example.moviesappsofttek.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val DATABASE_NAME = "App_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun providesDatabaseDao(appDatabase: AppDatabase) = appDatabase.databaseDao()
}