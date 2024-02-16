package com.example.moviesappsofttek.ui.favoritesmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesappsofttek.R
import com.example.moviesappsofttek.domain.models.movies.MovieDetailModel
import com.example.moviesappsofttek.core.utils.GlobalConstants

class FavoritesMoviesAdapter(private val movies: List<MovieDetailModel>, private val itemRecipeClickListener: FavoritesMoviesFragment):
    RecyclerView.Adapter<FavoritesMoviesAdapter.MoviesViewHolder>() {

    interface OnRecipeClickListener {
        fun onRecipeClick(movies: MovieDetailModel, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_recipe_favorite, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]

        holder.movie_name.text = movie.title
        holder.movie_date.text = movie.release_date
        Glide.with(holder.movie_image)
            .load(GlobalConstants.poster_path + movie.image)
            .centerCrop()
            .into(holder.movie_image);


        holder.itemView.setOnClickListener {
            itemRecipeClickListener.onRecipeClick(movie, position)
        }

    }

    override fun getItemCount(): Int = movies.size

    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val movie_name : TextView = itemView.findViewById(R.id.movie_name)
        val movie_date : TextView = itemView.findViewById(R.id.movie_fecha)
        val movie_image : ImageView = itemView.findViewById(R.id.movie_image)

    }


}