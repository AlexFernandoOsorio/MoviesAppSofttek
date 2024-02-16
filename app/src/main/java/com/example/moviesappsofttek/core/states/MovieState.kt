package com.example.moviesappsofttek.core.states

import com.example.moviesappsofttek.domain.models.movies.MovieModel


data class MovieState(
    val isLoading: Boolean = false,
    val data: MovieModel? = null,
    val error: String = ""
)
