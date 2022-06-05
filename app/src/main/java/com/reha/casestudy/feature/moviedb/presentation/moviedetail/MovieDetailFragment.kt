package com.reha.casestudy.feature.moviedb.presentation.moviedetail

import android.os.Bundle
import android.view.View
import com.reha.casestudy.BR
import com.reha.casestudy.R
import com.reha.casestudy.databinding.MovieDetailFragmentBinding
import com.reha.casestudy.feature.github.data.model.Repo
import com.rtx.framework.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, MovieDetailFragmentBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = arguments?.get("repo") as Repo
        observeViewModel()
        initUi(repo)
    }

    private fun observeViewModel() = viewModel.run {
    }

    private fun initUi(repo: Repo) {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.setRepoDetail(repo)
    }

    override fun getLayoutId() = R.layout.movie_detail_fragment

    override fun getBRViewModelId() = BR.viewModel

    override fun getViewModelClass() = MovieDetailViewModel::class.java
}
