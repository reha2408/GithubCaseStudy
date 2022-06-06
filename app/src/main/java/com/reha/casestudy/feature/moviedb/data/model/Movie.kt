package com.reha.casestudy.feature.moviedb.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("release_date") val release_date: String? = null,
) : Parcelable {

    fun getFullPosterPath() = "https://image.tmdb.org/t/p/original".plus(posterPath)

    fun getFullBackdropPath() = "https://image.tmdb.org/t/p/original".plus(backdropPath)
}