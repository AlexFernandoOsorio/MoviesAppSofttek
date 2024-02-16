package com.example.moviesappsofttek.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "tm_id") val id: Int,
    @ColumnInfo(name = "tm_title") val name: String,
    @ColumnInfo(name = "tm_image") val image: String,
    @ColumnInfo(name = "tm_description") val description: String,
    @ColumnInfo(name = "tm_popularity") val popularity: String,
    @ColumnInfo(name = "tm_release_date") val release_date: String,
    @ColumnInfo(name = "tm_genre") val genre: String,
    @ColumnInfo(name = "tm_is_favorite") val isFavorite: Boolean
)