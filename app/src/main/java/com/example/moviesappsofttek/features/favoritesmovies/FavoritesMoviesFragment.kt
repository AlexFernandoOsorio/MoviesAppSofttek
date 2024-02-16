package com.example.moviesappsofttek.features.favoritesmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesappsofttek.R
import com.example.moviesappsofttek.databinding.FragmentFavoritesMoviesBinding
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesMoviesFragment : Fragment(), FavoritesMoviesAdapter.OnRecipeClickListener {

    //Inicializamos binding y viewModel
    private lateinit var binding: FragmentFavoritesMoviesBinding
    private val viewModel: FavoritesMoviesViewModel by viewModels()

    //Inicializamos variables
    private lateinit var moviesAdapter: FavoritesMoviesAdapter
    private lateinit var movies: List<MovieDetailModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Realizamos una consulta a la base de datos para obtener la lista de peliculas favoritas
        viewModel.getMovieListFavoriteFromDb()
        //Bloque de codigo para el reciclerView
        binding.reciclerMoviesFavorites.layoutManager = GridLayoutManager(context, 2)
        //Observamos la lista de peliculas
        val listObserver = Observer<List<MovieDetailModel>> {
            movies = it
            moviesAdapter = FavoritesMoviesAdapter(movies, this)
            binding.reciclerMoviesFavorites.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
        }
        viewModel.moviesListModel.observe(viewLifecycleOwner, listObserver)

        //Observamos mensaje de error en caso de que la lista este vacia
        val errorObserver = Observer<String> {
            binding.message.text = it
            if (it == "") {
                binding.message.visibility = View.GONE
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)
    }

    //Funcion para navegar a la pantalla de detalle de pelicula
    override fun onRecipeClick(movieModel: MovieDetailModel, position: Int) {
        findNavController().navigate(
            R.id.action_favoritesMoviesFragment_to_movieDetailFragment,
            Bundle().apply {
                putInt("id", movieModel.id)
            })
    }

}