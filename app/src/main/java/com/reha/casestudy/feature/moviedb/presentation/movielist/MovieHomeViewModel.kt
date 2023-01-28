package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.github.data.response.SearchResultViewEntity
import com.reha.casestudy.feature.github.domain.interactor.GithubApiSearch
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.reha.casestudy.feature.moviedb.domain.interactor.MovieDbDiscover
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val movieDbDiscover: MovieDbDiscover
) : BaseViewModel() {

    private val movieHomeList = MutableLiveData<MutableList<MovieCategory>>()
    val movieHomeListLiveData: LiveData<MutableList<MovieCategory>> get() = movieHomeList

    val entity: MovieDiscoverViewEntity = MovieDiscoverViewEntity()

    fun initDiscoverMovies() {
        movieDbDiscover.execute(MovieDiscoverObserver(this, DiscoverType.POPULAR), MovieDbDiscover.Params(DiscoverType.POPULAR))
        movieDbDiscover.execute(MovieDiscoverObserver(this, DiscoverType.REVENUE), MovieDbDiscover.Params(DiscoverType.REVENUE))
        movieDbDiscover.execute(MovieDiscoverObserver(this, DiscoverType.TOP_RATED), MovieDbDiscover.Params(DiscoverType.TOP_RATED))
    }

    fun initDiscoverMovies2() {
        viewModelScope.launch {
            fetch(DiscoverType.REVENUE)
            delay(2000)
            fetch(DiscoverType.POPULAR)
            delay(3455)
            fetch(DiscoverType.TOP_RATED)
        }
    }

    private fun fetch(discoverType: DiscoverType) {
        movieDbDiscover.execute(
            MovieDiscoverObserver(this, discoverType),
            MovieDbDiscover.Params(discoverType)
        )
        handleMovieCategory(MovieCategory(discoverType, mutableListOf(Movie(),Movie(),Movie(),Movie(),Movie(),Movie())))
    }


    fun handleMovieCategory(movieCategory: MovieCategory) {
        entity.movieCategories.find { it.discoverType.id == movieCategory.discoverType.id }?.movieList?.apply {
            clear()
            addAll(movieCategory.movieList)
        }
        movieHomeList.value = entity.movieCategories.toMutableList()
    }
}
