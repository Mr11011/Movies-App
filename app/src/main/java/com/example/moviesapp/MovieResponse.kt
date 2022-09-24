package com.example.moviesapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies : List<Movie>,
    @SerializedName("total_pages")
    val pages: Int,
    @SerializedName("page")
    val page: Int,

) : Parcelable