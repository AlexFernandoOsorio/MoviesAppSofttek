package com.example.moviesappsofttek.features.movies

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesappsofttek.R
import com.example.moviesappsofttek.databinding.FragmentMoviesBinding
import com.example.moviesappsofttek.domain.models.movies.MovieModel
import com.example.moviesappsofttek.core.utils.GlobalConstants.api_key
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesAdapter.OnRecipeClickListener {
    //Inicializamos binding y viewModel
    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    //Inicializamos variables
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var movies: List<MovieModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Realizamos la llamada a la API para obtener la lista inicial de peliculas
        viewModel.getMovieListPopularFromApi(api_key)
        //Bloque de codigo para el reciclerView
        binding.reciclerMovies.layoutManager = LinearLayoutManager(context)
        //Observamos la lista de peliculas
        val listObserver = Observer<List<MovieModel>> {
            movies = it
            moviesAdapter = MoviesAdapter(movies, this)
            binding.reciclerMovies.adapter = moviesAdapter
            moviesAdapter.notifyDataSetChanged()
            //Si la lista esta vacia mostramos mensaje de Error
            if (movies.isEmpty()) {
                binding.message.visibility = View.VISIBLE
            }
        }
        viewModel.moviesListModel.observe(viewLifecycleOwner, listObserver)

        //Observamos mensaje de error
        val errorObserver = Observer<String> {
            binding.message.text = it
            if (it == "") {
                binding.message.visibility = View.GONE
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner, errorObserver)

        //Funcion para buscar peliculas con el SearchView
        setupSearchView()

    }

    //Funcion para buscar peliculas con el SearchView
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Al pulsar el boton de buscar se realiza la busqueda
                viewModel.getMovieListByNameFromApi(api_key, query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Cada vez que se escribe una letra se realiza la busqueda
                newText?.let {
                    viewModel.getMovieListByNameFromApi(api_key, newText)
                }
                return false
            }
        })
        //Al pulsar la X del SearchView se muestra la lista inicial
        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                //Al cerrar el SearchView se muestra la lista inicial
                viewModel.moviesListModel.value = emptyList()
                viewModel.getMovieListPopularFromApi(api_key)
                return false
            }
        })
    }

    override fun onRecipeClick(movieModel: MovieModel, position: Int) {
        //Al hacer click en una pelicula se navega a la pantalla de detalle
        findNavController().navigate(
            R.id.action_moviesFragment2_to_movieDetailFragment2,
            Bundle().apply {
                putInt("id", movieModel.id)
            })
    }

    override fun onResume() {
        super.onResume()
        //Al volver a la pantalla de peliculas se muestra la lista inicial
        viewModel.moviesListModel.value = emptyList()
        viewModel.getMovieListPopularFromApi(api_key)
    }

}
