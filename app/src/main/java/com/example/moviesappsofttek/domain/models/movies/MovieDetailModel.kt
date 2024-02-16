package com.example.moviesappsofttek.domain.models.movies

data class MovieDetailModel(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val popularity: Double,
    val release_date: String,
    val genre_ids: List<String>,
    val isFavorite : Boolean = false
)
