package com.reha.casestudy.feature.moviedb.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.reha.casestudy.feature.moviedb.domain.interactor.MovieDbDiscover
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val movieDbDiscover: MovieDbDiscover
) : BaseViewModel() {

    private val movieHomeList = MutableLiveData<MutableList<MovieCategory>>()
    val movieHomeListLiveData: LiveData<MutableList<MovieCategory>> get() = movieHomeList

    val entity: MovieDiscoverViewEntity = MovieDiscoverViewEntity()

    fun initDiscoverMovies() {
        movieDbDiscover.executeSilently(
            MovieDiscoverObserver(this, DiscoverType.POPULAR),
            MovieDbDiscover.Params(DiscoverType.POPULAR)
        )
        movieDbDiscover.executeSilently(
            MovieDiscoverObserver(this, DiscoverType.REVENUE),
            MovieDbDiscover.Params(DiscoverType.REVENUE)
        )
        movieDbDiscover.executeSilently(
            MovieDiscoverObserver(this, DiscoverType.TOP_RATED),
            MovieDbDiscover.Params(DiscoverType.TOP_RATED)
        )
    }

    fun handleMovieCategory(movieCategory: MovieCategory) {
        entity.movieCategories.find { it.discoverType.id == movieCategory.discoverType.id }?.movieList?.apply {
            clear()
            addAll(movieCategory.movieList)
        }
        movieHomeList.value = entity.movieCategories.toMutableList()
    }
}
