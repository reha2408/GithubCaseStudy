package com.reha.casestudy.feature.moviedb.presentation.movielist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.reha.casestudy.BR
import com.reha.casestudy.R
import com.reha.casestudy.databinding.MovieHomeFragmentBinding
import com.reha.casestudy.feature.github.data.model.Repo
import com.reha.casestudy.feature.moviedb.data.model.Movie
import com.reha.casestudy.feature.moviedb.data.model.MovieCategory
import com.reha.casestudy.feature.moviedb.presentation.MovieDbMainActivity
import com.rtx.framework.base.BaseFragment
import com.rtx.framework.extension.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieHomeFragment : BaseFragment<MovieHomeViewModel, MovieHomeFragmentBinding>() {

    private val movieHomeAdapter = MovieHomeAdapter(::onMovieSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initUi()
    }

    private fun observeViewModel() = viewModel.run {
        observeLiveData(movieHomeListLiveData) {
            movieHomeAdapter.submitList(it)
        }
    }

    private fun initUi() {
        val llManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.run {
            movieHomeList.layoutManager = llManager
            movieHomeList.adapter = movieHomeAdapter
        }
    }

    private fun onMovieSelected(item: MovieCategory) {
        // (activity as MovieDbMainActivity).showRepoDetailPage(item)
    }

    override fun getLayoutId() = R.layout.movie_home_fragment

    override fun getBRViewModelId() = BR.viewModel

    override fun getViewModelClass() = MovieHomeViewModel::class.java
}
