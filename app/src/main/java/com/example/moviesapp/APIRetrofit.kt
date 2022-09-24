package com.example.moviesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRetrofit {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private lateinit var retrofit : Retrofit

        fun getInstance() : Retrofit {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                return retrofit
        }
    }



}