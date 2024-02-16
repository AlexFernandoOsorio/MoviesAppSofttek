package com.example.moviesappsofttek.data.remote.mappers

import com.example.moviesappsofttek.data.remote.models.moviesDetail.MovieDetailDto
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel


fun MovieDetailDto.toDomainMovieDetail(): MovieDetailModel {
    return MovieDetailModel(
        id = this.id,
        title = this.title,
        image = this.poster_path,
        description = this.overview,
        popularity = this.popularity,
        release_date = this.release_date,
        genre_ids = this.genres.map { it.name }
    )
}