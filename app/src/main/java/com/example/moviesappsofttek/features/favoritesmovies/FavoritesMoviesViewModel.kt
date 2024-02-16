package com.example.moviesappsofttek.features.favoritesmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMoviesListFromDbUseCase
import com.example.moviesappsofttek.core.utils.GlobalConstants.noSavedMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesMoviesViewModel @Inject constructor() : ViewModel() {
    //Inicializo las variables  a utilizar en el fragment
    @Inject
    lateinit var GetMovieListFromApiUseCase: GetFavoriteMoviesListFromDbUseCase
    private var _moviesListModel = MutableLiveData<List<MovieDetailModel>>()
    var moviesListModel: LiveData<List<MovieDetailModel>> = _moviesListModel
    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage


    //Recupero la lista de peliculas favoritas de la base de datos
    fun getMovieListFavoriteFromDb() {
        viewModelScope.launch() {
            _moviesListModel.postValue(emptyList())
            val movieList = GetMovieListFromApiUseCase()
            _moviesListModel.postValue(movieList)

            if (movieList.isEmpty()) {
                _errorMessage.postValue(noSavedMovies)
            } else {
                _errorMessage.postValue("")
            }
        }
    }
}