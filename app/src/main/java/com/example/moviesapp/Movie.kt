package com.example.moviesapp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("backdrop_path")
    val backgroundImage: String?,

    @SerializedName("release_date")
    val release: String?,

    @SerializedName("vote_average")
    val rate: Float?,

    @SerializedName("overview")
    val overview:String?

    ,@SerializedName("description")
    val description:String?


) : Parcelable