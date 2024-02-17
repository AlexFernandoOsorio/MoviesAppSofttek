package com.example.moviesappsofttek.core.utils

import com.example.moviesappsofttek.BuildConfig

object GlobalConstants {

    //constantes para el apiservice
    const val BASE_URL = BuildConfig.BASE_URL_MOVIE
    const val api_key = BuildConfig.APPNAME_API_KEY
    const val poster_path = BuildConfig.BASE_URL_IMAGE

    //constantes para mensajes de error
    const val noInternet = "No hay conexión a internet"
    const val noSavedMovies = "No tienes Favoritos guardados"
    const val noSearchMovies =  "No se encontraron resultados para: "

    //constantes para repositorios
    const val errorNoUser = "Usuario y/o contraseña incorrectos"
}