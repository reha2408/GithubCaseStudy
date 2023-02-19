package com.reha.casestudy.feature.moviedb.presentation.moviedetail

import android.os.Bundle
import android.view.View
import com.reha.casestudy.BR
import com.reha.casestudy.R
import com.reha.casestudy.databinding.MovieDetailFragmentBinding
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.rtx.framework.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, MovieDetailFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.get("movie") as Movie
        observeViewModel()
        initUi(movie)
    }

    private fun observeViewModel() = viewModel.run {
    }

    private fun initUi(movie: Movie) {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.setMovieDetail(movie)
    }

    override fun getLayoutId() = R.layout.movie_detail_fragment

    override fun getBRViewModelId() = BR.viewModel

    override fun getViewModelClass() = MovieDetailViewModel::class.java
}
