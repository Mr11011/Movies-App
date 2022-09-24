package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : AppCompatActivity() {
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        backdrop = findViewById(R.id.movie_backdrop)
        poster = findViewById(R.id.movie_poster)
        title = findViewById(R.id.movie_title)
        rating = findViewById(R.id.movie_rating)
        releaseDate = findViewById(R.id.movie_release_date)
        overview = findViewById(R.id.movie_overview)
        recyclerViewSecond.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        val movieDetail = intent.getParcelableExtra<Movie>("MOVIE")
        if (movieDetail != null) {
            populateDetails(movieDetail)
        } else {
            finish()
        }
    }

    private fun populateDetails(movieDetails: Movie) {
        movieDetails.backgroundImage?.let { backdropPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280$backdropPath")
                .transform(CenterCrop())
                .into(backdrop)
        }
        movieDetails.poster.let { posterPath ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342$posterPath")
                .transform(CenterCrop())
                .into(poster)
        }
        title.text = movieDetails.title
        rating.rating = movieDetails.rate!!.div(2)
        releaseDate.text = movieDetails.release
        overview.text = movieDetails.overview


        val apiService = APIRetrofit.getInstance().create(APIServices::class.java)
        movieDetails.id?.let {
            apiService.getSimilarMovies(id = it)
                .enqueue(object : Callback<MovieResponse> {

                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            recyclerViewSecond.adapter =
                                response.body()?.movies?.let {
                                    MovieAdapter(
                                        it as MutableList<Movie>,
                                        onMovieClick = { movie -> showMovieDetails(movie) })
                                }
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        val error = t.message
                        println(error)
                    }
                })
        }


    }

    fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("MOVIE", movie)
        startActivity(intent)
    }

}


