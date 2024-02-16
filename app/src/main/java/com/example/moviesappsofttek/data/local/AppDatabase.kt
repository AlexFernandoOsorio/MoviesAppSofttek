package com.example.moviesappsofttek.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesappsofttek.data.local.dao.DatabaseDao
import com.example.moviesappsofttek.data.local.entities.AccountEntity
import com.example.moviesappsofttek.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class, AccountEntity::class],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    //Dao para acceder a las tablas
    abstract fun databaseDao(): DatabaseDao

}