package com.example.moviesappsofttek.features.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappsofttek.core.utils.GlobalConstants.noInternet
import com.example.moviesappsofttek.domain.models.movies.MovieModel
import com.example.moviesappsofttek.domain.usecase.movies.GetMoviesByNameFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieListFromApiUseCase
import com.example.moviesappsofttek.core.utils.GlobalConstants.noSearchMovies
import com.example.moviesappsofttek.core.utils.UIEvent
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMoviesListFromDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieListFromApiUseCase: GetMovieListFromApiUseCase,
    private val getMoviesByNameFromApiUseCase: GetMoviesByNameFromApiUseCase,
    private val getFavoriteMoviesListFromDbUseCase: GetFavoriteMoviesListFromDbUseCase
) : ViewModel() {

    //Inicializo las variables  a utilizar en el fragment
    private var _moviesListModel = MutableLiveData<List<MovieModel>>()
    var moviesListModel: MutableLiveData<List<MovieModel>> = _moviesListModel

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage

    private var _isError = MutableLiveData<Boolean>()
    var isError: MutableLiveData<Boolean> = _isError


    //Obtengo la lista de peliculas populares indexado por 20 del API
    fun getMovieListPopularFromApi(apiKey: String) {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = getMovieListFromApiUseCase.invoke(apiKey)
            movieList.collect { it ->
                when (it) {
                    is UIEvent.Loading -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }

                    is UIEvent.Success -> {
                        //Se completa la lista con las peliculas obtenidas de la API
                        _moviesListModel.postValue(it.data!!.toList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }

                    is UIEvent.Error -> {
                        //Si hay un error en la consulta a la API, se obtienen las peliculas favoritas guardadas
                        val localMovieDetailList = getFavoriteMoviesListFromDbUseCase()
                        //Se mapea la lista de peliculas favoritas guardadas a la lista de peliculas a mostrar
                        val localListMovie = localMovieDetailList.map { movieDetailModel ->
                            MovieModel(
                                id = movieDetailModel.id,
                                title = movieDetailModel.title,
                                image = movieDetailModel.image,
                                description = movieDetailModel.description,
                                popularity = movieDetailModel.popularity,
                                release_date = movieDetailModel.release_date,
                                genre_ids = movieDetailModel.genre_ids.map { it.length }
                            )
                        }
                        //Verificamos si la lista de peliculas favoritas guardadas tambien no está vacía
                        if (localListMovie.isNotEmpty()) {
                            //completamos la lista con las peliculas favoritas guardadas
                            _moviesListModel.postValue(localListMovie)
                            _errorMessage.postValue("")
                            isError.postValue(false)
                        } else {
                            // Si la base de datos también está vacía, indicar el mensaje de error
                            _moviesListModel.postValue(emptyList())
                            _errorMessage.postValue(noInternet)
                            isError.postValue(true)
                        }
                    }
                }
            }

        }
    }

    //Obtengo la lista de peliculas por nombre del API
    fun getMovieListByNameFromApi(apiKey: String, name: String) {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = getMoviesByNameFromApiUseCase(apiKey, name)
            movieList.collect {
                when (it) {
                    is UIEvent.Loading -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                    }

                    is UIEvent.Success -> {
                        _moviesListModel.postValue(it.data?.toList() ?: emptyList())
                        _errorMessage.postValue("")
                    }

                    is UIEvent.Error -> {
                        _moviesListModel.postValue(emptyList())
                        it.message = it.message
                        _errorMessage.postValue(noSearchMovies + name)
                    }
                }
            }

        }
    }
}