package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView2 = findViewById(R.id.recyclerView2)
        recyclerView2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        progressBar = findViewById(R.id.progressBar)
        adapter = MovieAdapter(mutableListOf()) { movie -> showMovieDetails(movie) }
        getMovieData()
        upComingMovie()
    }


    private fun getMovieData() {
        val apiService = APIRetrofit.getInstance().create(APIServices::class.java)
        apiService.getPopularMovies(page = 1)
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        progressBar.visibility = View.GONE
                        recyclerView.adapter =
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


    private fun upComingMovie() {
        val apiServices2 = APIRetrofit.getInstance().create(APIServices::class.java)
        apiServices2.getUpComingMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                progressBar.visibility = View.GONE
                recyclerView2.adapter = response.body()?.movies?.let {
                    MovieAdapter(
                        it as MutableList<Movie>,
                        onMovieClick = { movie -> showMovieDetails(movie) })
                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }


    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("MOVIE", movie)
        startActivity(intent)
    }


}

