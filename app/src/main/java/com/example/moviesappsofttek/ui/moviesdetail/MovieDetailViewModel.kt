package com.example.moviesappsofttek.ui.moviesdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.domain.usecase.movies.DeleteFavoriteMovieFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetFavoriteMovieByIdFromDbUseCase
import com.example.moviesappsofttek.domain.usecase.movies.GetMovieByIdFromApiUseCase
import com.example.moviesappsofttek.domain.usecase.movies.InsertFavoriteMovieToDbUseCase
import com.example.moviesappsofttek.core.utils.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(): ViewModel(){

    @Inject
    lateinit var GetMovieDetailFromApiUseCase: GetMovieByIdFromApiUseCase
    @Inject
    lateinit var insertFavoriteMovieToDb : InsertFavoriteMovieToDbUseCase
    @Inject
    lateinit var getFavoriteMovieByIdFromDbUseCase: GetFavoriteMovieByIdFromDbUseCase
    @Inject
    lateinit var deleteFavoriteMovieFromDb: DeleteFavoriteMovieFromDbUseCase

    //Inicializamos los livedata
    private var _movieModel = MutableLiveData<MovieDetailModel?>()
    var movieModel: MutableLiveData<MovieDetailModel?> = _movieModel

    private var _isFavorite = MutableLiveData<Boolean>()
    var isFavorite: MutableLiveData<Boolean> = _isFavorite



    //Funcion para obtener la lista de peliculas por Id mediante llamada a la api
    fun getMovieByIdFromApi(movieId : String ,apiKey : String) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieModel.postValue(null)
            val movieList = GetMovieDetailFromApiUseCase(movieId,apiKey)
            movieList.collect {
                when(it){
                    is UIEvent.Loading -> {
                        _movieModel.postValue(null)
                    }
                    is UIEvent.Success -> {
                        _movieModel.postValue(it.data!!)
                    }
                    is UIEvent.Error -> {
                        _movieModel.postValue(null)
                        it.message = "Error"
                    }
                }
            }

        }
    }

    //Funcion para agregar una pelicula a favoritos
    fun insertFavoriteMovie() {
        viewModelScope.launch() {
            insertFavoriteMovieToDb(movieModel.value!!)
        }
    }

    //Funcion para eliminar una pelicula de favoritos
    fun deleteFavoriteMovie() {
        viewModelScope.launch() {
            deleteFavoriteMovieFromDb(movieModel.value!!)
        }
    }
}