package com.example.moviesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*


class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val onMovieClick: ((movie: Movie) -> Unit)?
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.bind(currentMovie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private val imageBase = "https://image.tmdb.org/t/p/w500/"


        fun bind(movie: Movie) {

            itemView.movie_name.text = movie.title
            itemView.movie_rate.text = movie.rate.toString()
            itemView.rate_bar.rating = movie.rate!!.div(2)
            Glide
                .with(itemView)
                .load(imageBase + movie.poster)
                .into(itemView.movie_image)

            itemView.setOnClickListener {
                onMovieClick?.invoke(movie)
            }


        }


    }
}