package com.reha.casestudy.feature.moviedb.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieCategory(
    val id: Long? = null,
    val name: String? = null,
    val movieList: List<Movie>? = null,
    val page: Int? = null
) : Parcelable