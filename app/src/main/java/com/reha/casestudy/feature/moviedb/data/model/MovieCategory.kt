package com.reha.casestudy.feature.moviedb.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieCategory(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("movieList") val movieList: List<Movie>
) : Parcelable