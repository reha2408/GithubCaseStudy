package com.reha.casestudy.feature.moviedb.data.model

import android.os.Parcelable
import com.reha.casestudy.feature.moviedb.presentation.movielist.DiscoverType
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieCategory(
    val discoverType: DiscoverType,
    val movieList: MutableList<Movie> = mutableListOf(),
    val page: Int? = null
) : Parcelable