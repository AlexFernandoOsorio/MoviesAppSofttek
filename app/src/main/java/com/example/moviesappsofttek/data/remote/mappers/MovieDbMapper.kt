package com.example.moviesappsofttek.data.remote.mappers

import com.example.moviesappsofttek.data.local.entities.MovieEntity
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel

fun MovieEntity.toDomainMovieList() : MovieDetailModel {
    return MovieDetailModel(
        id = this.id,
        title = this.name,
        image = this.image,
        description = this.description,
        popularity = this.popularity.toDouble(),
        release_date = this.release_date,
        genre_ids = List(0) { this.genre},
    )
}
