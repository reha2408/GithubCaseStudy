package com.reha.casestudy.feature.moviedb.presentation.moviedetail

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.rtx.framework.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val pref: SharedPreferences) : BaseViewModel() {

    val movieDetail = ObservableField<Movie>()

    fun setMovieDetail(movie: Movie) = movieDetail.set(movie)
}
