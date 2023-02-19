package com.reha.casestudy.feature.moviedb.presentation.movielist

import com.reha.casestudy.feature.moviedb.data.model.MovieCategory

data class MovieDiscoverViewEntity(
    val movieCategories: List<MovieCategory> = listOf(
        MovieCategory(DiscoverType.POPULAR),
        MovieCategory(DiscoverType.REVENUE),
        MovieCategory(DiscoverType.TOP_RATED)
    )
)
